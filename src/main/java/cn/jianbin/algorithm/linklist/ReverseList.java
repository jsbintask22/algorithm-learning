package cn.jianbin.algorithm.linklist;

import cn.jianbin.alogthrim.datastructure.Node;

public class ReverseList {

    public static void main(String[] args) {
        // 反转链表
        Node head = Node.genRandom(10);
        head.print();

        Node reverse = reverse(head);
        System.out.println();
        reverse.print();

    }

    public static Node reverse(Node Node) {
        Node pre = null;
        Node cur = Node;

        Node tem = null;
        while (cur != null) {
            tem = cur.next;
            cur.next = pre;

            pre = cur;
            cur = tem;
        }

        return pre;
    }
}
