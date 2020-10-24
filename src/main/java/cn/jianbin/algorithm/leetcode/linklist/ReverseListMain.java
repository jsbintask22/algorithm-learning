package cn.jianbin.algorithm.leetcode.linklist;

import lombok.SneakyThrows;

/**
 * @author jianbin
 * @date 2020/10/20 19:44
 * 剑指 offer:输入一个链表，反转链表后，输出链表的所有元素。
 */
public class ReverseListMain {

    @SneakyThrows
    public static void main(String[] args) {
    }

    public static Node reverse(Node head) {
        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head.next;

            head.next = pre;

            pre = head;

            head = next;
        }

        return pre;
    }

    public static class Node {
        int v;
        Node next;
    }
}
