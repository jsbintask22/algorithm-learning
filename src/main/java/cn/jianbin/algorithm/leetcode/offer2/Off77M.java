package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.ListNode;

/**
 * @author jianbin.
 * @date 2023/4/22 15:37
 *
 * 剑指 Offer II 077. 链表排序
 * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 * 示例 2：
 *
 *
 *
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 *
 *
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 *
 *
 * 注意：本题与主站 148 题相同：https://leetcode-cn.com/problems/sort-list/
 */
public class Off77M {

    public static void main(String[] args) {

    }

    public ListNode sortList(ListNode head) {
        // 解法； 归并排序；
        // 1. 将链表拆分成 两个链表（从中间拆开）
        // 2. 一直拆到 每个小链表只有一个节点返回.
        // 3. 将每个只有一个节点的小链表合并后再返回，一直递归重复.

        return mergerSort(head);
    }

    public ListNode mergerSort(ListNode head) {
        // 如果当前只有 一个 节点，直接返回（拆到最小了)
        if (head == null || head.next == null) {
            return head;
        }

        // 拆分拿到 后半截.
        ListNode half = split(head);

        // 再分别对 前半截、后半截   继续拆分.
        ListNode h1 = mergerSort(head);
        ListNode h2 = mergerSort(half);

        // 把 两个半截 合并起来.
        return merger(h1, h2);
    }

    public ListNode merger(ListNode h1, ListNode h2) {
        if (h1 == null) {
            return h2;
        }

        if (h2 == null) {
            return h1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (h1 != null && h2 != null) {
            if (h1.val > h2.val) {
                // 把 h1 链起来.
                cur.next = h1;
                h1 = h1.next;
            } else {
                // 把h2 链起来.
                cur.next = h2;
                h2 = h2.next;
            }
            cur = cur.next;
        }

        // 出来以后，检查哪个链表 没有链完.
        if (h1 != null) {
            cur.next = h1;
        }
        if (h2 != null) {
            cur.next = h2;
        }

        // 返回头节点.
        return dummy.next;
    }

    public ListNode split(ListNode head) {
        // 拆分链表为 小链表后  【返回】 后半截，因为前半截是传进来的，调用者肯定持有了.
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 出来以后，slow 就是 中间 位置.
        ListNode ret = slow.next;
        // 断开 slow 的next（拆分)
        slow.next = null;
        
        return ret;
    }
}
