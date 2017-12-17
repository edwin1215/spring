package com.edwin.spring.web.algorithm.interesting;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * DNA合成
 */
public class DnaSynthesis {

    public static char[] element = {'A', 'T', 'C', 'G'};

    public static void main(String[] args) {
//        String dna1 = getDNA(10);
//        String dna2 = getDNA(10);
//        System.out.println(dna1 + " " + dna2);

        Scanner scanner = new Scanner(System.in);
        String dna1 = scanner.next();
        String dna2 = scanner.next();
        Map<Character, Character> matching = new HashMap<>();
        matching.put('A', 'T');
        matching.put('T', 'A');
        matching.put('C', 'G');
        matching.put('G', 'C');
        int count = 0;
        for (int i = 0; i < dna1.length(); i++) {
            char c1 = dna1.charAt(i);
            char c2 = dna2.charAt(i);
            if (c2 != matching.get(c1)) {
                count++;
            }
        }
        System.out.println(count);
    }


    public static String getDNA(int size) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            buffer.append(element[random.nextInt(4)]);
        }
        return buffer.toString();
    }
}
