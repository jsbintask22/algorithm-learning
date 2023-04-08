package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.ListNode;
import lombok.experimental.UtilityClass;

import java.util.Stack;

/**
 * @author jianbin.
 * @date 2023/4/8 14:42
 *
 * 剑指 Offer II 025. 链表中的两数相加
 * 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 *
 *
 * 示例1：
 *
 *
 *
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 * 示例2：
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 * 示例3：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 *
 * 提示：
 *
 * 链表的长度范围为 [1, 100]
 * 0 <= node.val <= 9
 * 输入数据保证链表代表的数字无前导 0
 *
 *
 * 进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。
 *
 *
 *
 * 注意：本题与主站 445 题相同：https://leetcode-cn.com/problems/add-two-numbers-ii/
 */
@UtilityClass
public class Off25M {

    public static void main(String[] args) {
        ListNode h1 = ListNode.gen(3);
        ListNode h2 = ListNode.gen(5);
        ListNode.print(h1);
        ListNode.print(h2);

        ListNode.print(solution(h1, h2));
    }

    public ListNode solution(ListNode h1, ListNode h2) {
        // 解法：反转两个链表 再从 各位依次加到 首位，构成一个新的链表；
        // 解法2： 不改变原有的链表结构； 使用一个栈 把链表压入后弹出；

        Stack<ListNode> s1 = new Stack<>();
        while (h1 != null) {
            s1.push(h1);
            h1 = h1.next;
        }

        Stack<ListNode> s2 = new Stack<>();
        while (h2 != null) {
            s2.push(h2);
            h2 = h2.next;
        }

        // 获得两个栈的长度； 然后弹出；
        ListNode lastNode = null;
        int last = 0;

        while (!s1.empty() || !s2.empty() || last > 0) {
            int t1 = s1.empty() ? 0 : s1.pop().val;
            int t2 = s2.empty() ? 0 : s2.pop().val;

            int val = t1 + t2 + last;
            ListNode newHead = new ListNode(val % 10);
            newHead.next = lastNode;
            lastNode = newHead;
            last = val / 10;
        }

        return lastNode;
    }


}
