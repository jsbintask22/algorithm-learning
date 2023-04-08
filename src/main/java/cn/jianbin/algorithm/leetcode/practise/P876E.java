package cn.jianbin.algorithm.leetcode.practise;

import cn.jianbin.algorithm.leetcode.offer2.data.ListNode;
import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/8 16:07
 *
 * 876. 链表的中间结点
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[3,4,5]
 * 解释：链表只有一个中间结点，值为 3 。
 * 示例 2：
 *
 *
 * 输入：head = [1,2,3,4,5,6]
 * 输出：[4,5,6]
 * 解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。
 *
 *
 * 提示：
 *
 * 链表的结点数范围是 [1, 100]
 * 1 <= Node.val <= 100
 */
@UtilityClass
public class P876E {

    public static void main(String[] args) {
        ListNode head = ListNode.gen(4);
        ListNode.print(head);

        ListNode.print(solution(head));
    }

    public ListNode solution(ListNode head) {
        // 解法：快慢指针； 快指针每次走 2，慢指针每次走 1，等到快指针到 终点了，慢指针则在 中间位置；
        // 1 2 3 4 -> 3
        // 1 2 3 4 5 -> 3
        // 1 2 3 4 5 6 -> 3

        // 要不要加这个 dummy，关键在于 偶数情况下，想让 slow 落在 前面一个还是 后面一个； 1 2 3 4   落在 2 还是 3
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode slow = head;
        ListNode fast = dummy;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
