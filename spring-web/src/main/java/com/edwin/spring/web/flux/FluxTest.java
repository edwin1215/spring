package com.edwin.spring.web.flux;

import com.edwin.spring.web.utils.DoraemonKit;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author junming.cao
 * @date 2022/2/22 9:07 下午
 */
@Slf4j
public class FluxTest {

    private static final ThreadPoolExecutor OUT_EXECUTOR = new ThreadPoolExecutor(4, 4, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("out-flux-test-thread-%d").build());
    private static final ThreadPoolExecutor INNER_EXECUTOR = new ThreadPoolExecutor(4, 4, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("inner-flux-test-thread-%d").build());


    private static void simpleCreation() {
        List<String> words = Arrays.asList(
                "the",
                "quick",
                "brown",
                "fox",
                "jumped",
                "over",
                "the",
                "lazy",
                "dog"
        );


        Flux<String> fewWords = Flux.just("Hello", "World");
        Flux<String> manyWords = Flux.fromIterable(words);

        fewWords.subscribe(log::info);
        log.info("-------------");
        manyWords.subscribe(log::info);
    }

    private static void testBaseSubscribe() {
        Flux.range(1, 10)
                .doOnRequest(r -> log.info("request of {}", r))
                .subscribe(new SampleSubscriber<>());
    }

    private static void testSubscribe() {
        Disposable subscribe = Flux.range(3, 5)
                .map(i -> {
                    if (i < 8) {
                        return i;
                    }
                    throw new RuntimeException("Got to 5");
                })
                .doOnRequest(a -> log.info("request of {}", a))
                .doOnNext(a -> log.info("doOnNext : {}", a))
                .subscribe(a -> log.info("finish {}", a),
                        throwable -> log.error("error:", throwable),
                        () -> log.info("completed"),
                        sub -> sub.request(10));


    }

    private static void testPublishOn() {
        final int timeout = 5;
        Flux.just("tom")
                .map(s -> {
                    log.info("map operate.");
                    return s.concat("@mail.com");
                })
                .publishOn(Schedulers.newElastic("new-publishOn-thread"))
                .filter(s -> {
                    DoraemonKit.sleep(timeout);
                    log.info("filter operate.");
                    return s.startsWith("t");
                })
                .subscribeOn(Schedulers.newElastic("new-subscribeOn-thread"))
                .subscribe(s -> {
                    DoraemonKit.sleep(timeout);
                    log.info("subscribe operate.");
                    log.info(s);
                });
    }

    @Slf4j
    static class SampleSubscriber<T> extends BaseSubscriber<T> {

        public void hookOnSubscribe(Subscription subscription) {
            log.info("Subscribed");
            request(1);
        }

        public void hookOnNext(T value) {
            log.info("value : {}", value);
            cancel();
        }
    }




    private static void async() {

        sharding()
//                .map(r -> r.stream().allMatch(flag -> flag))
                .doOnSuccess(r -> log.info("success :{}", r))
                .doOnError(e -> log.error("外部flux doOnError.", e))
                .doFinally(r -> log.info("外部flux doFinally, r :{}", r))
                .block();
        log.info("我是底线");
    }

    private static Mono<List<Boolean>> sharding() {
//        List<Integer> datasource = getDatasource();
//        for (Integer i : datasource) {
//            doTask(i).subscribe();
//
//        }

        return Flux.fromIterable(getDatasource())
                .parallel(4)
                .runOn(Schedulers.fromExecutor(OUT_EXECUTOR))
                .flatMap(FluxTest::doTask)
                .sequential()
                .collectList();
    }

    private static List<Integer> getDatasource() {
        List<Integer> result = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            result.add(i + 1);
        }
        return result;
    }

    private static Mono<Boolean> doTask(int seconds) {
        return Mono.fromFuture(CompletableFuture.supplyAsync(() -> {
                    DoraemonKit.sleep(seconds % 3);
                    return Boolean.TRUE;
                }, OUT_EXECUTOR))
                .publishOn(Schedulers.fromExecutor(INNER_EXECUTOR))
                .doOnSuccess(r -> log.info("task-{}执行完毕。", seconds))
                .doOnError(e -> log.error("task-{}执行异常。", seconds, e));
    }




    public static void main(String[] args) {
//        testPublishOn();
//        simpleCreation();
//        testSubscribe();
//        testBaseSubscribe();
        async();
    }
}
