package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.ListNode;
import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/4/6 9:07 PM
 *
 *
题解 (541)
提交记录
剑指 Offer II 024. 反转链表
给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。



示例 1：


输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
示例 2：


输入：head = [1,2]
输出：[2,1]
示例 3：

输入：head = []
输出：[]


提示：

链表中节点的数目范围是 [0, 5000]
-5000 <= Node.val <= 5000


进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？



注意：本题与主站 206 题相同： https://leetcode-cn.com/problems/reverse-linked-list/


 */
@UtilityClass
public class Off24E {

    public static void main(String[] args) {
        ListNode gen = ListNode.gen(5);
        ListNode.print(gen);

        ListNode.print(solution(gen));
    }

    public ListNode solution(ListNode head) {
        // 反转链表；
        // 方法1； 定义多个变量；
        // 方法2： 递归；
        if (head == null || head.next == null) {
            return head;
        }

        // 1->2->3
        ListNode pre = null;

        while (head != null) {
            // 把当前节点下一个 几点存储起来；    next -> 2
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }

        return pre;
    }

    public ListNode solution2(ListNode head) {
        // 递归解决；
        if (head == null || head.next == null) {
            return head;
        }

        // 1 -> 2 -> 3 -> 4 -> 5
        ListNode newHead = solution2(head);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
