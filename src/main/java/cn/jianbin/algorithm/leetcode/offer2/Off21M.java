package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.ListNode;
import lombok.experimental.UtilityClass;

import java.util.Random;

/**
 * @author aaron.zou
 * @date 2023/4/6 7:29 PM
 *
 * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 *  
 *
 * 示例 1：
 *
 *
 *
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *  
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *  
 *
 * 进阶：能尝试使用一趟扫描实现吗？
 *
 *  
 *
 * 注意：本题与主站 19 题相同： https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/SLwz0R
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@UtilityClass
public class Off21M {

    public static void main(String[] args) {
        ListNode head = ListNode.gen(5);
        ListNode.print(head);

        ListNode.print(solution(head, 5));


    }

    public ListNode solution(ListNode head, int n) {
        if (n == 0) {
            return head;
        }

        // 干掉链表的倒数第 n 个节点；
        // head = [1,2,3,4,5], n = 2
        // 准备两个初始指针； 一个在 head ； 一个在 head + n的位置；
        // 等到 fast 到最后了，删掉前面那个节点的后面一个；

        ListNode slow = new ListNode(-1);
        ListNode dummy = slow;
        // 这样做可以防止 "溢出"
        slow.next = head;
        ListNode fast = slow;
        int s = n;

        // fast 指针先移动 n 个位置；
        while (s > 0) {
            fast = fast.next;
            s--;
        }

        // 一直移动到 fast 的下一个为空.
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // 此时，slow 的下一个节点即为要移除的节点；
        ListNode remove = slow.next;
        slow.next = remove.next;

        return dummy.next;
    }

    public ListNode gen(int n) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;

        while (n > 0) {
            cur.next = new ListNode(new Random().nextInt(10));
            cur = cur.next;
            n--;
        }

        return head.next;
    }

    public void print(ListNode head) {
        if (head == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        while (head.next != null) {
            sb.append(head.val).append("->");
            head = head.next;
        }

        sb.append(head.val);

        System.out.println(sb);
    }

}
