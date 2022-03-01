package com.edwin.spring.web.flux;

import com.edwin.spring.web.utils.DoraemonKit;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 背压测试
 *
 * @author junming.cao
 * @date 2022/2/25 3:39 下午
 */
@Slf4j
public class PressureTest {
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(4, 4, 60,
            TimeUnit.SECONDS, new LinkedBlockingQueue<>(100),
            new ThreadFactoryBuilder().setNameFormat("pressure-test-thread-%d").build());

    private static void test() {
        Flux.interval(Duration.ofMillis(100))
                .subscribeOn(Schedulers.fromExecutor(EXECUTOR))
                .subscribe(new Subscriber<Long>() {
                    Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription s) {
                        this.subscription = s;
                        subscription.request(2);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        subscription.request(1);
                        log.info("onNext val:{}", aLong);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        DoraemonKit.sleep(100);
    }

    private static void test2() {
        Flux.interval(Duration.ofMillis(100))
                .onBackpressureBuffer(10)
                .doOnNext(i -> {
                    log.info("do {}", i);
                    DoraemonKit.sleep(1);
                })
                .subscribe();
        DoraemonKit.sleep(100);
    }

    public static void main(String[] args) {
        test2();
    }
}
