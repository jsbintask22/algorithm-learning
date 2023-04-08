package cn.jianbin.algorithm.leetcode.offer2.data;

import cn.jianbin.algorithm.leetcode.offer2.Off21M;

import java.util.Random;

/**
 * @author aaron.zou
 * @date 2023/4/6 8:10 PM
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }



    public static ListNode gen(int n) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;

        while (n > 0) {
            cur.next = new ListNode(new Random().nextInt(10));
            cur = cur.next;
            n--;
        }

        return head.next;
    }

    public static void print(ListNode head) {
        if (head == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        while (head.next != null) {
            sb.append(head.val).append("->");
            head = head.next;
        }

        sb.append(head.val);

        System.out.println(sb);
    }

    public static String toStr(ListNode head) {
        if (head == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        while (head.next != null) {
            sb.append(head.val).append("->");
            head = head.next;
        }

        sb.append(head.val);

        return sb.toString();
    }
}
