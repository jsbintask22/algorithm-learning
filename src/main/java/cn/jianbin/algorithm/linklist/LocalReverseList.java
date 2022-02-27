package cn.jianbin.algorithm.linklist;

import cn.jianbin.alogthrim.datastructure.Node;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author aaron.zou
 * @date 2022/1/12 9:47 下午
 *
 * 局部链表反转
 *
 * 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
 *
 * 传入 3 变成
 *
 * 3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7 -> 8
 */
@UtilityClass
public class LocalReverseList {
    public static void main(String[] args) {
        Node head = Node.genOrdered(10);
        head.print();

        Node fourNode = getKEndNode(head, 4);
        fourNode.print();

        Node sixNode = reverseNode(head, head.next.next.next.next.next);
        sixNode.print();


        System.out.println("------------------------------------------------");
        head = Node.genOrdered(11);
        Node reverseKGroupNode = reverseKGroupNode(head, 3);
        reverseKGroupNode.print();
    }

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int ret = 0;
        Map<Character, Integer> map = new HashMap<>();

        // abcabca
        for (int end = 0, start = 0; end < chars.length; end++) {
            char c = chars[end];
            // 如果字符已经包含在 map 中了，说明 start - end + 1 要开始重复了。 要替换 start的值为最新位置
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c));
            }
            ret = Math.max(ret, (end - start + 1));
            map.put(c, end + 1);
        }

        return ret;
    }

    /**
     *  从 start开始，返回第 k 个节点
     */
    public Node getKEndNode(Node start, int k) {
        while (--k > 0 && start != null) {
            start = start.next;
        }

        return start;
    }

    public Node reverseKGroupNode(Node head, int k) {
        if (head == null) {
            return null;
        }

        // 先判断第一次有没有 k 各节点
        Node end = getKEndNode(head, k);
        if (end == null) {
            return head;
        }

        Node start = head;

        // 先局部反转 前 k 个节点
        Node reversedHead = reverseNode(start, end);

        // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7
        // 3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7

        while (start.next != null) {
            Node curNext = start.next;
            Node temp = getKEndNode(curNext, k);
            if (temp == null) {
                return reversedHead;
            }

            start.next = reverseNode(curNext, temp);
            start = curNext;
        }


        return reversedHead;
    }

    /**
     *  局部反转 start 到 end的节点
     *  start -> end -> end.next
     *
     *  end -> start -> end.next
     */
    public Node reverseNode(Node start, Node end) {
        Node endNext = end.next;

        Node pre = null;
        Node cur = start;

        // 是否到局部最后面一个节点（正常整个链表反转是 判空）
        while (cur != endNext) {
            Node temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        start.next = endNext;

        return pre;
    }

}
