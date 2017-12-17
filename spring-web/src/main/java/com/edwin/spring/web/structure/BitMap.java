package com.edwin.spring.web.structure;

/**
 * 位图
 *
 * @author caojunming
 * @data 2017年11月23日 上午11:00:37
 */
public class BitMap {

    /**
     * int类型4字节=32位
     */
    public static final int INT_MAX_BIT = 32;
    /**
     * 位图最大位数(表示的最大值)
     */
    public int maxNum;
    /**
     * 根据位图的最大位数/32位得到的bocket
     */
    public int[] bArr;

    public BitMap(int maxNum) {
        bArr = new int[maxNum / INT_MAX_BIT + 1];
        this.maxNum = maxNum;
    }

    public void set(int a) {
        bArr[a / INT_MAX_BIT] |= (1 << a % INT_MAX_BIT);
        System.out.println(Integer.toBinaryString(bArr[a / INT_MAX_BIT]));
    }

    public int get(int a) {
        return (bArr[a / INT_MAX_BIT] & (1 << a % INT_MAX_BIT)) != 0 ? 1 : 0;
    }

    public static void main(String[] args) {
        int max = 10000;
        BitMap bitMap = new BitMap(max);
        bitMap.set(9999);
        bitMap.set(0);
        bitMap.set(2);
        bitMap.set(135);
        System.out.println("-------begin-------");
        for (int i = 0; i < max; i++) {
            int j = bitMap.get(i);
            if (j > 0) {
                System.out.println(i);
            }
        }
        System.out.println("-------end-------");
    }
}
