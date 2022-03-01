package com.edwin.spring.web.play;

import com.edwin.spring.web.utils.DoraemonKit;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Map;

/**
 * 纳什均衡
 *
 * A、B 两人猜硬币，硬币有正反两个面
 * A、B 出不同的面，对应金钱调整
 * ┏━━━━━┳━━━━━━━━┳━━━━━━━━┓
 * ┃ A\B ┃   T    ┃   F    ┃
 * ┣━━━━━╋━━━━━━━━╋━━━━━━━━┫
 * ┃  T  ┃ +3,-3  ┃ -2,+2  ┃
 * ┣━━━━━╋━━━━━━━━╋━━━━━━━━┫
 * ┃  F  ┃ -2,+2  ┃ +1,-1  ┃
 * ┗━━━━━┻━━━━━━━━┻━━━━━━━━┛
 *
 * @author junming.cao
 * @date 2022/3/1 7:55 下午
 */
@Slf4j
public class NashEquilibriumTest {

    public static void main(String[] args) {
        int t = 0, f = 0;
        RandomChoice choice = new RandomChoice();
        for (int i = 0; i < 10000; i++) {
            if (choice.choice(Role.builder().base(2).ratio(1).build())) {
                t++;
            } else {
                f++;
            }
        }
        log.info("t:{},f:{}", t, f);
    }

    interface Person {
        boolean play(Role role, boolean result);

    }

    static abstract class AbstractPerson implements Person {
//        protected Map<String, Boolean>
        protected int money = 1000;
    }

    /**
     * 赌徒们
     */
    static abstract class GamblerDecorator extends AbstractPerson {
        protected AbstractPerson decorator;
        protected ChoiceStrategy strategy;

        public GamblerDecorator(ChoiceStrategy strategy, AbstractPerson decorator){
            this.decorator = decorator;
        }
    }

    static class Gambler extends GamblerDecorator {
        public Gambler(ChoiceStrategy strategy, AbstractPerson decorator) {
            super(strategy, decorator);
        }

        @Override
        public boolean play(Role role, boolean result) {
            return false;
        }
    }

    /**
     * 庄家
     */
    static class Dealer extends AbstractPerson {
        private ChoiceStrategy strategy;

        public Dealer(ChoiceStrategy strategy) {
            this.strategy = strategy;
        }

        @Override
        public boolean play(Role role, boolean result) {
            return strategy.choice(role);
        }

        public void settlement() {

        }
    }

    @Data
    @Builder
    @ToString
    static class Role {
        private int times;
        private int base;
        private int ratio;
    }


    interface ChoiceStrategy {

        boolean choice(Role role);
    }

    static class RandomChoice implements ChoiceStrategy {
        @Override
        public boolean choice(Role role) {
            return DoraemonKit.random(role.getBase(), role.getRatio());
        }
    }
}
