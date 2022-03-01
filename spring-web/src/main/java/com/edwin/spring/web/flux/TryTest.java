package com.edwin.spring.web.flux;

import com.edwin.spring.web.utils.DoraemonKit;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class TryTest {

    public static void main(String[] args) {
//        extracted();
        log.info("test result:{}", test());
    }

    private static int test() {

        return Try.of(TryTest::getString)
                .filter(StringUtils::isNotBlank)
                .map(Integer::parseInt)
                .filter(a -> a > 0)
                .getOrElseThrow(e -> {
                    log.error("shardingNum参数有误", e);
                    throw new RuntimeException("shardingNum参数有误");
                });
    }

    private static String getString() {
        return "1";
    }

    private static void extracted() {
        Try.of(() -> {
                    DoraemonKit.sleep(1);
                    log.info("123");
                    return true;
                })
                .onSuccess(a -> {
                    DoraemonKit.sleep(1);
                    log.info("success, a:{}", a);
                });
        log.info("finish");
    }
}
