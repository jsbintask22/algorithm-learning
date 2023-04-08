package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.ListNode;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;

/**
 * @author jianbin.
 * @date 2023/4/8 15:12
 * <p>
 * 剑指 Offer II 026. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * <p>
 * L0 → L1 → … → Ln-1 → Ln
 * 请将其重新排列后变为：
 * <p>
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * <p>
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 * 示例 2:
 * <p>
 * <p>
 * <p>
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 * <p>
 * <p>
 * 注意：本题与主站 143 题相同：https://leetcode-cn.com/problems/reorder-list/
 */
@UtilityClass
public class Off26M {

    public static void main(String[] args) {
        ListNode h1 = ListNode.gen(4);
        ListNode.print(h1);

        solution(h1);
        ListNode.print(h1);

        ListNode h2 = ListNode.gen(5);
        ListNode.print(h2);

        solution2(h2);
        ListNode.print(h2);


        ListNode j3 = ListNode.gen(4);
        ListNode.print(j3);

        solution2(j3);
        ListNode.print(j3);
    }

    public void solution(ListNode head) {
        // 1 -> 2 -> 3 -> 4
        // 1 -2 -> 3

        // 解法1：用一个 双端队列，把链表压如，再用双指针从 队列两端交换数据
        ArrayList<ListNode> deque = new ArrayList<>();
        while (head != null) {
            deque.add(head);
            head = head.next;
        }


        int start = 0;
        int end = deque.size() - 1;

        while (start < end) {
            // 1 2 3 4
            ListNode startNode = deque.get(start);
            startNode.next = deque.get(end);
            start++;
            startNode.next.next = deque.get(start);
            end--;
        }

        // 把中间的 （3） 的next干掉（它是最后一个节点了）
        deque.get(start).next = null;
    }

    public static void solution2(ListNode head) {
        // 1 2 3 4 5   -> 1 5 2 4 3
        // 1 2 3 4 -> 1 4 2 3
        // 解法2； 找到链表的 中间节点； 3，再从 3 开始把链表后半截反转了  5 -> 4 -> 3    1 -> 2 -> 3
        // 再从 两边开始 合并链表；
        ListNode middleNode = findMiddle(head);

        // 1 -> 2 -> 3 <- 4
        ListNode end = reverse(middleNode.next);
        // 这里要重新置位 null，否则无限循环了
        middleNode.next = null;

        merger(head, end);
    }

    private ListNode findMiddle(ListNode head) {
        if (head == null) {
            return null;
        }

        // 1 2 3 4 -> 2
        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }

        return pre;
    }

    private ListNode merger(ListNode h1, ListNode h2) {
        // 1 -> 2
        // 5 -> 4 -> 3

        ListNode ret = new ListNode(-1);
        ret.next = h1;

        while (h1 != null && h2 != null) {
            ListNode temp1 = h1.next;
            ListNode temp2 = h2.next;

            h1.next = h2;
            h1 = temp1;

            h2.next = h1;
            h2 = temp2;
        }

        return ret.next;
    }
}
