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
        header = sl.reversalList(header);
        sl.systemOut(header);
    }

    /**
     * 解题思路：用快慢指针寻找链表中间点
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode pre = slow.next;
        while (pre != null && pre.next != null) {
            ListNode current = pre.next;

        }
    }

    public ListNode reversalList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head; // 指针不动
        ListNode post = head.next; // 每次后移pre.next
        while (post != null) {
            pre.next = post.next;
            post.next = head;
            head = post;
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
