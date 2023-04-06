package cn.jianbin.algorithm.leetcode.offer2.data;

import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/4/6 8:50 PM
 */
@UtilityClass
public class Off23E {

    public static void main(String[] args) {
        
    }
    
    public ListNode solution(ListNode h1, ListNode h2) {
        int l1 = len(h1);
        int l2 = len(h2);
        // 方案：先获得两个 链表长度； 相减，再让长的链表先走 查值这么多不，再一起走，最后如果相遇则返回；

        int abs = Math.abs(l1 - l2);
        ListNode pre = l1 > l2 ? h1 : h2;

        while (abs-- > 0) {
            pre = pre.next;
        }

        ListNode que = l1 > l2? h2 : h1;

        if (que == pre) {
            // 可能这时候已经到一起了；
            return pre;
        }

        while (que != null && pre != null) {
            que = que.next;
            pre = pre.next;

            if (que == pre) {
                return que;
            }
        }

        return null;
    }

    public int len(ListNode head) {
        // 返回链表长度；
        int ret = 0;
        while (head != null) {
            ret++;
            head = head.next;
        }

        return ret;
    }
}
