package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.ListNode;
import lombok.experimental.UtilityClass;

import java.util.Stack;

/**
 * @author jianbin.
 * @date 2023/4/8 17:20
 *
 * 剑指 Offer II 027. 回文链表
 * 给定一个链表的 头节点 head ，请判断其是否为回文链表。
 *
 * 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入: head = [1,2,3,3,2,1]
 * 输出: true
 * 示例 2：
 *
 *
 *
 * 输入: head = [1,2]
 * 输出: false
 *
 *
 * 提示：
 *
 * 链表 L 的长度范围为 [1, 105]
 * 0 <= node.val <= 9
 *
 *
 * 进阶：能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 *
 *
 * 注意：本题与主站 234 题相同：https://leetcode-cn.com/problems/palindrome-linked-list/
 */
@UtilityClass
public class Off27E {

    public static void main(String[] args) {
        ListNode h1 = ListNode.gen(3);
        ListNode.print(h1);

        System.out.println(solution(h1));

        System.out.println(solution2(h1));

    }

    public boolean solution2(ListNode head) {
        // 找到中间节点； 反转后部分； 两边遍历判断；  恢复链表；
        // 1 2 3 4
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 反转后半部分；
        ListNode tempHead = slow.next;
        ListNode reverseHead = slow.next;
        ListNode pre = null;
        while (tempHead != null) {
            ListNode temp = tempHead.next;
            tempHead.next = pre;
            pre = tempHead;
            tempHead = temp;
        }

        slow.next = null;

        // 两边便利；
        ListNode left = head;
        ListNode right = pre;

        while (left != null && right != null) {
            if (left.val != right.val) {
                return false;
            }
            left = left.next;
            right = right.next;
        }

        // 恢复右半部分；
        ListNode pree = null;
        while (pre != null) {
            ListNode temp = pre.next;
            pre.next = pree;
            pree = pre;
            pre =temp;
        }

        slow.next = pree;

        return true;
    }

    public boolean solution(ListNode head) {
        // 解法：找到中间节点，反转后部分节点，再一次便利是否相等；
        // 解法2： 把链表加到 栈中，出栈的同时便利同节点

        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        // 3 / 2 = 1   4 / 2 = 2
        int middle = stack.size() / 2;

        while (middle > 0) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
            middle--;
        }

        return true;
    }
}
