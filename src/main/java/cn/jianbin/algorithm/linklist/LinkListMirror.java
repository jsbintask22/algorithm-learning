package cn.jianbin.algorithm.linklist;

import cn.jianbin.alogthrim.datastructure.Node;
import lombok.experimental.UtilityClass;

import java.util.Stack;

/**
 * @author aaron.zou
 * @date 2022/2/8 8:34 下午
 *
 * 回文 链表
 * 1 2 3 2 1
 * 1 2 3 3 2 1
 * 1 1
 *
 * 1 2 2 1
 *
 */
@UtilityClass
public class LinkListMirror {
    public static void main(String[] args) {
        Node root = Node.gen("1,2,3,4,5,4,3,2,1,1");
        root.print();
        System.out.println(isMirrorList(root));
        root.print();
        System.out.println(isMirrorWithStack(root));
    }

    /**
     *  判断是否回文链表； 使用 栈
     */
    public boolean isMirrorWithStack(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            if (cur.getVal() != stack.pop().getVal()) {
                return false;
            }
            cur = cur.next;
        }

        return true;
    }

    public boolean isMirrorList(Node head) {
        if (head == null) {
            return false;
        }

        // 先找到 中点 和 最后一点
        Node left = head;

        Node mid = head;
        Node right = head;
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        if (right.next != null) {
            right = right.next;
        }

        // 再将 mid - right 反转, 不包括mid
        Node pre = mid;
        Node cur = mid.next;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 将 mid.next 指向空
        mid.next = null;

        // 此时 pre = right;
        // 开始 遍历 left 和 right； 是否值相等
        boolean ret = true;
        while (left != null) {
            if (left.getVal() != right.getVal()) {
                ret = false;
                break;
            }

            left = left.next;
            right = right.next;
        }

        // 最后，要还原 mid - right
        Node pree = null;
        cur = pre;
        while (cur != mid) {
            Node next = cur.next;
            cur.next = pree;
            pree = cur;
            cur = next;
        }

        mid.next = pree;

        return ret;
    }

    /**
     *  返回链表的 中间节点 OR 中间偏上（如果是偶数）
     */
    public Node midOrUpMid(Node head) {
        // 如果只有 0 1 2 个节点 返回自己
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }



}
