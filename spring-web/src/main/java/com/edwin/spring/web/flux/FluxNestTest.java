package com.edwin.spring.web.flux;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Flux
 *
 * @author junming.cao
 * @date 2022/2/28 8:19 下午
 */
@Slf4j
public class FluxNestTest {

    private static final Integer QUEUE_SIZE = 10000;

    private static final ThreadPoolExecutor ONE = new ThreadPoolExecutor(4, 4, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(QUEUE_SIZE),
            new ThreadFactoryBuilder().setNameFormat("one-test-thread-%d").build());
    private static final ThreadPoolExecutor TWO = new ThreadPoolExecutor(4, 4, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(QUEUE_SIZE),
            new ThreadFactoryBuilder().setNameFormat("two-test-thread-%d").build());
    private static final ThreadPoolExecutor THREE = new ThreadPoolExecutor(4, 4, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(QUEUE_SIZE),
            new ThreadFactoryBuilder().setNameFormat("three-test-thread-%d").build());


    private static final int NUM = 1000;

    private static List<Integer> getSource() {
        List<Integer> result = Lists.newArrayList();
        for (int i = 0; i < NUM; i++) {
            result.add(i + 1);
        }
        return result;
    }

    private static Mono<Integer> testFlux(List<Integer> list) {
        return Flux.fromIterable(Lists.partition(list, NUM / 10))
                .parallel(4)
                .runOn(Schedulers.fromExecutor(ONE))
                .flatMap(a -> {
                    log.info("testFlux - flatMap1 - doOnSuccess");
                    return testFlux0(a);
                })
                .sequential()
                .collectList()
//                .publishOn(Schedulers.fromExecutor(ONE))
                .doOnSuccess(integers -> log.info("testFlux - flatMap2 - doOnSuccess"))
                .doOnError((e -> log.error("testFlux - flatMap2 - doOnError", e)))
                .flatMap(o -> Mono.just(o.stream().mapToInt(i -> i).sum()))
                .doOnSuccess(integers -> log.info("testFlux - flatMap3 - doOnSuccess"))
                .doOnError((e -> log.error("testFlux - flatMap3 - doOnError", e)));
    }

    private static Mono<Integer> testFlux0(List<Integer> list) {
        return Flux.fromIterable(Lists.partition(list, NUM / 100))
                .parallel(4)
                .runOn(Schedulers.fromExecutor(TWO))
                .flatMap(a -> {
                    log.info("testFlux0 - flatMap1 - doOnSuccess");
                    return testFlux1(a);
                })
                .sequential()
                .collectList()
//                .publishOn(Schedulers.fromExecutor(TWO))
                .doOnSuccess(integers -> log.info("testFlux0 - flatMap2 - doOnSuccess"))
                .doOnError((e -> log.error("testFlux0 - flatMap2 - doOnError", e)))
                .flatMap(o -> Mono.just(o.stream().mapToInt(i -> i).sum()))
                .doOnSuccess(integers -> log.info("testFlux0 - flatMap3 - doOnSuccess"))
                .doOnError((e -> log.error("testFlux0 - flatMap3 - doOnError", e)));
    }


    private static Mono<Integer> testFlux1(List<Integer> list) {
        return Flux.fromIterable(list)
                .parallel(4)
                .runOn(Schedulers.fromExecutor(THREE))
                .flatMap(Mono::just)
                .sequential()
                .collectList()
                .doOnSuccess(integers -> log.info("testFlux1 - flatMap1 - doOnSuccess"))
                .doOnError((e -> log.error("testFlux1 - flatMap1 - doOnError", e)))
                .flatMap(o -> Mono.just(o.stream().mapToInt(i -> i).sum()))
                .doOnSuccess(integers -> log.info("testFlux1 - flatMap2 - doOnSuccess"))
                .doOnError((e -> log.error("testFlux1 - flatMap2 - doOnError", e)));
    }

    public static void main(String[] args) {
        testFlux(getSource())
                .doOnSuccess(r -> log.info("doOnSuccess {}", r))
                .doOnError(e -> log.error("doOnError ", e))
                .subscribe();
    }
}
