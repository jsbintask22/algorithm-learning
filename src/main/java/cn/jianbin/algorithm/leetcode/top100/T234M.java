package cn.jianbin.algorithm.leetcode.top100;

import cn.jianbin.algorithm.leetcode.offer2.data.ListNode;

import java.util.List;

/**
 * @author jianbin.
 * @date 2023/7/30 10:39
 */
public class T234M {

    public static void main(String[] args) {
        ListNode head = ListNode.gen(100);

        System.out.println(new T234M().isPalindrome(head));
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    public boolean isPalindrome(ListNode head) {
        // 1 - 2 - 3 - 4 - 5
        int len = len(head);

        // 反转后半段；
        int spli = len / 2;
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null && spli-- > 0) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        // 1 <- 2 <- 3   4 -> 5

        if (len % 2 == 1) {
            cur = cur.next;
        }

        while (prev != null && cur != null) {
            if (prev.val != cur.val) {
                return false;
            }
        }

        return true;
    }

    public int len(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }

        return len;
    }

}
