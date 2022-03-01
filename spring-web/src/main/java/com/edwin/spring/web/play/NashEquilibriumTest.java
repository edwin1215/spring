package com.edwin.spring.web.play;

import com.edwin.spring.web.utils.DoraemonKit;
import com.google.common.collect.Maps;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 纳什均衡
 * <p>
 * A、B 两人猜硬币，硬币有正反两个面
 * A、B 出不同的面，对应金钱调整
 * ┏━━━━━┳━━━━━━━━┳━━━━━━━━┓
 * ┃ A\B ┃   T    ┃   F    ┃
 * ┣━━━━━╋━━━━━━━━╋━━━━━━━━┫
 * ┃  T  ┃ +3,-3  ┃ -2,+2  ┃
 * ┣━━━━━╋━━━━━━━━╋━━━━━━━━┫
 * ┃  F  ┃ -2,+2  ┃ +1,-1  ┃
 * ┗━━━━━┻━━━━━━━━┻━━━━━━━━┛
 * <p>
 * 结论：B 只要保证 正反比 = 3:5，大概率会赚钱
 *
 * @author junming.cao
 * @link https://briwisdom.blog.csdn.net/article/details/83927742
 * @date 2022/3/1 7:55 下午
 */
@Slf4j
public class NashEquilibriumTest {

    public static void main(String[] args) {
        Player player1 = new Player(new Role(2, 1));
        Player player2 = new Player(new Role(8, 3));
        new Gamble(player1, player2).startGame(800);
    }

    static class Gamble {

        private static final Map<Integer, Pair<Integer, Integer>> ROLE_MAP;

        private static final int TRUE_TRUE = 3;
        private static final int TRUE_FALSE = 2;
        private static final int FALSE_TRUE = 1;
        private static final int FALSE_FALSE = 0;

        static {
            ROLE_MAP = Maps.newHashMapWithExpectedSize(4);
            ROLE_MAP.put(TRUE_TRUE, Pair.of(3, -3));
            ROLE_MAP.put(TRUE_FALSE, Pair.of(-2, 2));
            ROLE_MAP.put(FALSE_TRUE, Pair.of(-2, 2));
            ROLE_MAP.put(FALSE_FALSE, Pair.of(1, -1));
        }


        private final AbsPlayer one;
        private final AbsPlayer two;

        public Gamble(AbsPlayer one, AbsPlayer two) {
            this.one = one;
            this.two = two;
        }


        public void startGame(int times) {
            for (int i = 0; i < times; i++) {
                int oneToss = one.toss();
                int twoToss = two.toss();
                int result = oneToss << 1 | twoToss;
                one.trade(ROLE_MAP.get(result).getLeft());
                two.trade(ROLE_MAP.get(result).getRight());
            }
            log.info("玩家 1 余额:{}，玩家 2 余额:{}", one.balance(), two.balance());
            log.info("玩家 1 情况:{}", one.watch());
            log.info("玩家 2 情况:{}", two.watch());
        }
    }

    static abstract class AbsPlayer {
        private int money = 100;

        public abstract int toss();

        public void trade(int money) {
            this.money += money;
        }

        public int balance() {
            return this.money;
        }

        public abstract String watch();
    }

    static class Player extends AbsPlayer {
        private final ChoiceStrategy strategy;
        private final Pair<AtomicInteger, AtomicInteger> watcher = Pair.of(new AtomicInteger(), new AtomicInteger());

        public Player(Role role) {
            strategy = new RandomChoice(role);
        }

        @Override
        public int toss() {
            boolean toss = strategy.toss();
            if (toss) {
                watcher.getLeft().incrementAndGet();
            } else {
                watcher.getRight().incrementAndGet();
            }
            return BooleanUtils.toInteger(toss);
        }

        @Override
        public String watch() {
            return String.format("正面一共 %s 次，反面一共 %s 次。", watcher.getLeft().get(), watcher.getRight().get());
        }
    }

    @Data
    @Builder
    @ToString
    static class Role {
        private int base;
        private int ratio;
    }

    interface ChoiceStrategy {
        boolean toss();
    }

    static class RandomChoice implements ChoiceStrategy {
        private final Role role;

        public RandomChoice(Role role) {
            this.role = role;
        }

        @Override
        public boolean toss() {
            return DoraemonKit.random(role.getBase(), role.getRatio());
        }
    }
}
