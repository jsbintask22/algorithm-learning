package cn.jianbin.algorithm.leetcode.linklist;

import lombok.Data;
import lombok.SneakyThrows;

/**
 * @author jianbin
 * @date 2020/10/2 17:49
 *
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumAddMain {

    @SneakyThrows
    public static void main(String[] args) {

    }

    public static Node addLinkList(Node p, Node q) {
        Node result = new Node();
        Node resultBak = result;
        result.val = 0;

        int carry = 0;
        while (p.next != null || q.next != null) {
            int v1 = p.next != null? p.val : 0;
            int v2 = q.next != null ? q.val : 0;
            int sum = v1 + v2 + carry;

            carry = sum / 10;
            result.val = sum % 10;
            result.next = new Node();
            result.next.val = 0;
            result = result.next;

            if (p.next != null) {
                p = p.next;
            }
            if (q.next != null) {
                q = q.next;
            }
        }

        if (carry > 0) {
            result = new Node();
            result.val = carry;
        }

        return resultBak;
    }

    @Data
    public static class Node {
       private int val;
       private Node next;
    }
}
