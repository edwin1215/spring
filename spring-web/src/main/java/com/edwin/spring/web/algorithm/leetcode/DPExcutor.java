package com.edwin.spring.web.algorithm.leetcode;

import com.alibaba.fastjson.JSON;
import com.edwin.spring.web.algorithm.TreeNode;

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

    public boolean find(int [][] array, int target) {
        if (array == null || array.length <= 0 || array[0].length <= 0) {
            return false;
        }
        int height = array.length, width = array[0].length;
        int hPoi = 0, wPoi = width - 1;
        while (hPoi < height && wPoi >= 0) {
            int num = array[hPoi][wPoi];
            if (num == target) {
                return true;
            } else if (num < target) {
                hPoi++;
            } else {
                wPoi--;
            }
        }
        return false;
    }

    public String replaceSpace(StringBuffer str) {
        if (str == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i ++) {
            if (str.charAt(i) == ' ') {
                builder.append("%20");
            } else {
                builder.append(str.charAt(i));
            }
        }
        return builder.toString();
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length <= 0 || inorder.length <= 0
                || preorder.length != inorder.length) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        int inIdx = 0;
        for (int i = 1; i < preorder.length; i ++) {
            TreeNode temp = st.peek();
            if (temp.val != inorder[inIdx]) {
                temp.left = new TreeNode(preorder[i]);
                st.push(temp.left);
            } else {
                while (!st.isEmpty() && st.peek().val == inorder[inIdx]) {
                    temp = st.pop();
                    inIdx ++;
                }
                temp.right = new TreeNode(preorder[i]);
                st.push(temp.right);
            }
        }
        return root;
    }

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre == null || in == null
                || pre.length <= 0 || in.length <= 0
                || pre.length != in.length) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        int inIdx = 0;
        for (int i = 1; i < pre.length; i ++) {
            TreeNode temp = st.peek();
            if (temp.val != in[inIdx]) {
                temp.left = new TreeNode(pre[i]);
                st.push(temp.left);
            } else {
                while (!st.isEmpty() && st.peek().val == in[inIdx]) {
                    temp = st.pop();
                    inIdx ++;
                }
                temp.right = new TreeNode(pre[i]);
                st.push(temp.right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        DPExcutor e = new DPExcutor();
//        e.numWays(4);*.iml
//        System.out.println(e.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));

//        e.bfs(buildBfs(), "A");
//        System.out.println("----------");
//        e.dfs(buildBfs(), "A");

//        System.out.println(e.replaceSpace(new StringBuffer("we are winner")));
        System.out.println(JSON.toJSONString(e.buildTree(new int[]{1, 4, 7, 9, 10, 8, 6, 5, 3, 2}, new int[]{9, 7, 4, 8, 10, 6, 1, 3, 2, 5})));
        DPExcutor excutor = new DPExcutor();
        System.out.println("");
    }
}
