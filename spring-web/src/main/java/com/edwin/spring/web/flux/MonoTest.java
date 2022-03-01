package com.edwin.spring.web.flux;

import com.edwin.spring.web.utils.DoraemonKit;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StopWatch;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author junming.cao
 * @date 2022/2/22 9:23 下午
 */
@Slf4j
public class MonoTest {

    private static final ThreadPoolExecutor OUT_EXECUTOR = new ThreadPoolExecutor(4, 4, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("out-test-thread-%d").build());
    private static final ThreadPoolExecutor INNER_EXECUTOR = new ThreadPoolExecutor(4, 4, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("inner-test-thread-%d").build());


    private static void testSubscribe() {
        Mono.fromFuture(source())
                .filter(StringUtils::isNotBlank)
                .map(a -> {
                    log.info("get source:{}", a);
                    return a;
                })
                .doOnSuccess(a -> log.info("doOnSuccess, {}", a))
                .doOnError(e-> log.error("doOnError.", e))
                .subscribeOn(Schedulers.fromExecutor(new ThreadPoolExecutor(4, 4, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20))))
                .subscribe();

    }

    private static void testBlock() {
        Mono<Tuple2<Long, Integer>> mono = Mono.just(2)
                .publishOn(Schedulers.parallel())
                .map(i -> {
                    log.info("first map.");
                    DoraemonKit.sleep(1);
                    return i * 2;
                })
                .map(i -> {
                    log.info("first map.");
                    return i - 2;
                })
                .elapsed();
        MonoTest.log.info("-----------------start-----------------");
        mono.block();
        MonoTest.log.info("-----------------finish-----------------");
//        mono.subscribe();
//        mono.subscribe
    }

    private static void test() {
        Mono.just("123")
                .filter(a -> a.startsWith("1"))
                .map(a -> {
                    String concat = a.concat("@qq.com");
                    sendEmail(concat);
                    return concat;
                })
                .doOnSuccess(a -> log.info("deal [{}]", a))
                .subscribeOn(Schedulers.immediate())
                .subscribe();
        log.info("-------just do it");
        DoraemonKit.sleep(4);

    }

    private static void sendEmail(String email) {
        log.info("already send email to [{}]", email);
    }

    private static CompletableFuture<String> source() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("runAsync");
            if (DoraemonKit.random(10, 9)) {
//                throw new RuntimeException();
            }
            return "runAsync";
        }).whenComplete(((r, e) -> {
            if (e != null) {
                log.error("source error. ", e);
            } else {
                log.info("source success. {}", r);
            }
        }));
    }
    public static void main(String[] args) {
//        testBlock();
//        testSubscribe();
//        extracted();
        testDefaultEmpty();
    }

    private static void testDefaultEmpty() {
        testDefaultEmpty0()
                .defaultIfEmpty(Boolean.FALSE)
                .doOnSuccess(s -> log.info("testDefaultEmpty doOnSuccess : {}", s))
                .doOnError(e -> log.error("testDefaultEmpty doOnError", e))
                .subscribe();
    }

    private static Mono<Boolean> testDefaultEmpty0() {
        return Mono.just(testDefaultEmpty1())
                .filter(CollectionUtils::isNotEmpty)
                .flatMap(list -> {
                    if (DoraemonKit.random(10, 10)) {
                        throw new RuntimeException();
                    }
                    log.info("flatMap, list:{}", list);
                    return Mono.just(list.stream().allMatch(b -> b));
                })
//                .defaultIfEmpty(Boolean.FALSE)
                .doOnSuccess(r -> log.info("testDefaultEmpty0 doOnSuccess : {}", r))
                .doOnError(e -> log.error("testDefaultEmpty0 doOnError", e));

    }

    private static List<Boolean> testDefaultEmpty1() {
        if (DoraemonKit.random(10, 10)) {
//            throw new RuntimeException();
        }
        return Lists.newArrayList(true);
    }

    private static void extracted() {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start("all");
        if (!stopWatch.isRunning()) {
            stopWatch.start("all");
        }
        stopWatch.stop();
        stopWatch.start("步骤1");
        DoraemonKit.sleep(1);
        stopWatch.stop();
        stopWatch.start("步骤2");
        DoraemonKit.sleep(1);
        stopWatch.stop();

        if (stopWatch.isRunning()) {
            stopWatch.stop();
        }
        log.info("{}", stopWatch.prettyPrint());
    }

}
