package com.edwin.spring.web.algorithm.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class DPExcutor {


    private int numWays(int n) {
        // int[] temp = new int[n+1];
        // return dp(n, temp);
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int a = 1, b = 2, temp = 0;
        for (int i = 3; i < n; i ++) {
            temp = a;
            a = b;
            b = temp + a;
        }
        return a + b;
    }

    private int dp(int n, int[] temp) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        if (temp[n] > 0) {
            return temp[n];
        }
        temp[n] = (dp(n - 1, temp) + dp(n - 2, temp)) % 1000000007;
        return temp[n];
    }

    private int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                nums[i] = Math.max(nums[i -1] + nums[i], nums[i]);
            }
            max = Math.max(max, nums[i]);
        }
        System.out.println(JSON.toJSONString(nums));
        return max;
    }

    private void bfs(Map<String, String[]> args, String node) {
        Queue<String> q = new ArrayDeque<>();
        Set<String> set = new HashSet<>();
        q.add(node);
        set.add(node);
        while (!q.isEmpty()) {
            String poll = q.poll();
            System.out.println(poll);
            String[] strings = args.get(poll);
            if (strings == null || strings.length < 1) {
                continue;
            }
            for (String pn : strings) {
                if (set.contains(pn)) {
                    continue;
                }
                set.add(pn);
                q.add(pn);
            }
        }
    }

    private void dfs(Map<String, String[]> args, String node) {
        Stack<String> stack = new Stack<>();
        Set<String> set = new HashSet<>();
        stack.push(node);
        set.add(node);
        while (!stack.isEmpty()) {
            String poll = stack.pop();
            System.out.println(poll);
            String[] strings = args.get(poll);
            if (strings == null || strings.length < 1) {
                continue;
            }
            for (String pn : strings) {
                if (set.contains(pn)) {
                    continue;
                }
                set.add(pn);
                stack.push(pn);
            }
        }
    }

    private static Map<String, String[]> buildBfs() {
        Map<String, String[]> map = new HashMap<>();
        map.put("A", new String[]{"B", "C"});
        map.put("B", new String[]{"A", "E"});
        map.put("C", new String[]{"A", "D", "E"});
        map.put("D", new String[]{"C", "E"});
        map.put("E", new String[]{"B", "C", "D", "F"});
        map.put("F", new String[]{"E"});
        return map;
    }

    private void bfsPro(Map<String, String[]> args, String node) {
        Queue<String> q = new PriorityQueue<>();
        Set<String> set = new HashSet<>();
        q.add(node);
        set.add(node);
        while (!q.isEmpty()) {
            String poll = q.poll();
            System.out.println(poll);
            String[] strings = args.get(poll);
            if (strings == null || strings.length < 1) {
                continue;
            }
            for (String pn : strings) {
                if (set.contains(pn)) {
                    continue;
                }
                set.add(pn);
                q.add(pn);
            }
        }
    }

    public static void main(String[] args) {
        DPExcutor e = new DPExcutor();
//        e.numWays(4);
//        System.out.println(e.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

        e.bfs(buildBfs(), "A");
        System.out.println("----------");
        e.dfs(buildBfs(), "A");

    }
}
