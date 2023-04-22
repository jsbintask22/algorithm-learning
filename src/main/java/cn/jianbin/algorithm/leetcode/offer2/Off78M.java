package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.ListNode;
import lombok.experimental.UtilityClass;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author jianbin.
 * @date 2023/4/22 17:10
 * 剑指 Offer II 078. 合并排序链表
 * 给定一个链表数组，每个链表都已经按升序排列。
 *
 * 请将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 *
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 *
 * 提示：
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 *
 * 注意：本题与主站 23 题相同： https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
@UtilityClass
public class Off78M {

    public static void main(String[] args) {

    }

    public ListNode mergeKLists2(ListNode[] lists) {
        // 解法2； 把链表中的每个值加到 栈 （容器）中， 设置优先级队列，每次都从 栈 中弹出，
        PriorityQueue<ListNode> smallHeap = new PriorityQueue<>(Comparator.comparingInt(v -> v.val));

        // 先把每个个 链表的 首节点 加到 队列中.
        int len = lists.length;

        while (len-- > 0) {
            if (lists[len] != null) {
                smallHeap.offer(lists[len]);
            }
        }

        // 再弹出小根堆，每次弹出的都是最小的。加到结果中.
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (!smallHeap.isEmpty()) {
            ListNode min = smallHeap.poll();
            cur.next = min;
            cur = cur.next;

            // 弹出来的 节点如果 还有next，就要把 next 加进去继续组成小根堆.
            if (min.next != null) {
                smallHeap.offer(min.next);
            }
        }

        return dummy.next;

    }




    public ListNode mergeKLists(ListNode[] lists) {
        // 解法； 想象 2 个链表如何合并， 每次拿出 两个中最小的一个，然后等于 next

        return merger(lists);
    }



    public ListNode merger(ListNode[] lists) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (notAllNull(lists)) {
            // 找出 当前每个链表中最小的 节点.
            cur.next = findMin(lists);
            cur = cur.next;
        }

        return dummy.next;
    }

    public ListNode findMin(ListNode[] list) {
        // 找出最小的 节点，并且让当前节点等于 下一个.
        ListNode min = new ListNode(Integer.MAX_VALUE);
        int minIdx = -1;

        for (int i = 0; i < list.length; i++) {
            ListNode listNode = list[i];

            if (listNode != null && listNode.val < min.val) {
                min = listNode;
                minIdx = i;
            }
        }

        // 数组中的 最小位置 要指向下一个节点.
        list[minIdx] = list[minIdx].next;

        // min 的next要干掉.
        min.next = null;

        return min;
    }

    public boolean notAllNull(ListNode[] lists) {
        // 检查是否 全部为空。 只要有一个 不为空 返回 true
        for (ListNode list : lists) {
            if (list != null) {
                return true;
            }
        }

        return false;
    }
}
