package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.ListNode;
import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/8 20:49
 * <p>
 * <p>
 * 剑指 Offer II 029. 排序的循环链表
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
 * <p>
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 * <p>
 * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 * <p>
 * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * <p>
 * 输入：head = [3,4,1], insertVal = 2
 * 输出：[3,4,1,2]
 * 解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [], insertVal = 1
 * 输出：[1]
 * 解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
 * 示例 3：
 * <p>
 * 输入：head = [1], insertVal = 0
 * 输出：[1,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= Number of Nodes <= 5 * 10^4
 * -10^6 <= Node.val <= 10^6
 * -10^6 <= insertVal <= 10^6
 * <p>
 * <p>
 * 注意：本题与主站 708 题相同： https://leetcode-cn.com/problems/insert-into-a-sorted-circular-linked-list/
 */
@UtilityClass
public class Off29M {


    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(3, new ListNode(5, head));

        ListNode solution = solution(head, 0);
        System.out.println(solution);
    }

    public ListNode solution(ListNode head, int insertVal) {
        if (head == null) {
            return new ListNode(insertVal);
        }

        if (head.next == null) {
            // 下一个为空，不构成循环；
            head.next = new ListNode(insertVal, head);
            return head;
        }

        // 解法：如果  pre <= insertVal <= next 插入；
        // 或者   pre >= next && insertVal <= next || insertVal >= pre

        ListNode cur = head;
        ListNode next = cur.next;

        while (true) {
            // 恰好卡在 中间； 大于前一个，小于后一个；
            if (cur.val <= insertVal && insertVal <= next.val) {
                cur.next = new ListNode(insertVal, next);
                return head;
            }

            // 第二种情况； 遇到转折点了（它是最大的）;
            // 小于前一个，并且大于后一个； 并且 当且小于 < 下一个（转折点）
            if (next.val < cur.val && (insertVal <= next.val || insertVal >= cur.val)) {
                cur.next = new ListNode(insertVal, next);
                return head;
            }

            ListNode nnext = next.next;
            cur = next;
            next = nnext;
        }

    }

}
