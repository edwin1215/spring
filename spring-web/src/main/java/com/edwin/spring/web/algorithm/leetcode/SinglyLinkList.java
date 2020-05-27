package com.edwin.spring.web.algorithm.leetcode;

import java.util.HashMap;

/**
 * 给定一个单项链表，L0,L1,L2,L3...Ln-1,Ln
 * 重新排序成L0,Ln,L1,Ln-1,L2,Ln-2...
 * 在不改变节点值的情况下完成此操作
 */
public class SinglyLinkList {

    public static void main(String[] args) {
        ListNode header = new ListNode(1);
        ListNode tempHeader = header;
        for (int i = 1; i < 10; i++) {
            ListNode temp = new ListNode(i + 1);
            tempHeader.next = temp;
            tempHeader = temp;
        }

        SinglyLinkList sl = new SinglyLinkList();
        sl.systemOut(header);
//        header = sl.reversalList(header);
        sl.reorderList(header);
        sl.systemOut(header);
    }

    /**
     * 解题思路：
     * 1、用快慢指针寻找链表中间点
     * 2、将后半段链表反转
     * 3、前后半段链表做合并
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 寻找中间点
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 中间点
        ListNode pre = slow;
        ListNode post = slow.next;
        while (post != null) {
            pre.next = post.next;
            post.next = slow;
            slow = post;
            post = pre.next;
        }

        // head前半段头指针
        // slow后台段头指针
        // 每一合并后指针后移
        post = slow;
        pre = head;
        ListNode temp;
        while (pre != null && post != null) {
            temp = pre.next;
            pre.next = post;
            pre = temp;
            temp = post.next;
            post.next = pre;
            post = temp;
        }
    }

    public ListNode reversalList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head; // pre指针不动
        ListNode post = head.next; // post每次后移pre.next
        while (post != null) {
            pre.next = post.next;
            post.next = head;
            head = post; // 每次都将head更新为头指针
            post = pre.next;
        }
        return head;
    }

    public void systemOut(ListNode header) {
        ListNode temp = header;
        while (temp != null) {
            System.out.print(temp.val + ", ");
            temp = temp.next;
        }
        System.out.println();
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
