package com.edwin.spring.web.algorithm.interesting;

/**
 * <h1>扔鸡蛋问题</h1>
 * <p>
 * 一幢 100 层的大楼，给你两枚鸡蛋。假设，在第 n 层扔下鸡蛋，鸡蛋不碎，那么从第 n-1 层扔鸡蛋，鸡蛋也不会碎。
 * 两个鸡蛋一模一样，不碎的话可以扔无数次。目标是利用这两个鸡蛋找出临界楼层 t , 使得鸡蛋从 t 层扔下不会碎，
 * 从 t+1 层扔下会碎。
 * 现要求回答， 最少需要多少次尝试， 才能保证在最坏的情况下，找到楼层 t ， 且需要给出尝试的策略。
 */
public class ThrowEgg {

    public static void main(String[] args) {
        int floors = 100;
        int eggs = 4;

        System.out.println(computeEggBreak(eggs, floors));
    }

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    private static int computeEggBreak(int eggs, int floors) {
        int table[][] = new int[eggs + 1][floors + 1];

        // boundary condition:
        // if no floors or 1 floors, only need 0 trails or 1 trails
        for (int i = 0; i <= eggs; i++) {
            table[i][1] = 1;
            table[i][0] = 0;
        }

        // if only one egg,   f(1,k) = k
        for (int j = 0; j <= floors; j++) {
            table[1][j] = j;
        }

        // for the rest of cases
        // f( eggs, floors) = 1+ Max(f( eggs-1 , floors-1), f( eggs, floors-x))
        // x is the floor number we choose to drop for current attempt
        // range of i = 1,2,.....,floors,
        for (int i = 2; i <= eggs; i++) {
            for (int j = 2; j <= floors; j++) {

                table[i][j] = Integer.MAX_VALUE;  // so important

                for (int floorTriedFirst = 1; floorTriedFirst <= j; floorTriedFirst++) {
                    int res = 1 + max(table[i - 1][floorTriedFirst - 1],
                            table[eggs][j - floorTriedFirst]);
                    if (res < table[i][j]) {
                        table[i][j] = res;
                    }
                }
            }
        }
        return table[eggs][floors];
    }
}
