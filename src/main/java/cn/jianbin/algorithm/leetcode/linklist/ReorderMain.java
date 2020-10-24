package cn.jianbin.algorithm.leetcode.linklist;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianbin
 * @date 2020/10/20 19:27
 */
public class ReorderMain {

    @SneakyThrows
    public static void main(String[] args) {

    }

    public Node reOrder(Node head) {
        if (head == null) {
            return head;
        }

        List<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int start = 0;
        int end = list.size() - 1;

        while (start < end) {
            list.get(start).next = list.get(end);
            start++;

            if (start == end) {
                break;
            }

            list.get(end).next = list.get(start);
            end--;
        }

        return head;
    }


    public static class Node {
        int value;
        Node next;
    }
}
