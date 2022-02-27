package cn.jianbin.algorithm.linklist;

import cn.jianbin.alogthrim.datastructure.DNode;
import cn.jianbin.alogthrim.datastructure.LDeque;
import cn.jianbin.alogthrim.datastructure.LStack;
import cn.jianbin.alogthrim.datastructure.Node;
import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2022/1/10 10:36 下午
 */
@UtilityClass
public class LinkLists {

    public static void main(String[] args) {
        Node head = Node.genRandom(10);
        head.print();

        reversed(head).print();

        System.out.println();
        DNode head2 = DNode.genRandom(3);
        head2.print();

        reversed(head2).print();

        System.out.println();

        // 队列； 先进先出
        LDeque lDeque = new LDeque();
        for (int i = 0; i < 10; i++) {
            lDeque.offer(i);
        }

        while (!lDeque.isEmpty()) {
            System.out.print(lDeque.poll().getVal() + " ");
        }

        System.out.println();
        LStack lStack = new LStack();
        for (int i = 0; i < 10; i++) {
            lStack.offer(i);
        }

        while (!lStack.isEmpty()) {
            System.out.print(lStack.poll().getVal() + " ");
        }
    }

    /**
     *  反转链表
     */
    public Node reversed(Node head) {
        if (head == null) {
            return null;
        }

        Node pre = null;
        // 上游不能变，所以这里要用一个新变量
        Node cur = head;
        while (cur != null) {
            Node temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }

    /**
     *  反转双链表
     */
    public DNode reversed(DNode head) {
        if (head == null) {
            return null;
        }

        DNode pre = null;
        // 上游不能变，所以这里要用一个新变量
        DNode cur = head;
        while (cur != null) {
            DNode curNext = cur.next;

            cur.next = pre;
            cur.pre = curNext;

            pre = cur;
            cur = curNext;
        }

        return pre;
    }
}
