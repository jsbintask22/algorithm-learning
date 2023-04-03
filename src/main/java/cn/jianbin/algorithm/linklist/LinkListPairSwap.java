package cn.jianbin.algorithm.linklist;

import cn.jianbin.alogthrim.datastructure.Node;

public class LinkListPairSwap {

    public static void main(String[] args) {
        // 反转链表
        Node head = Node.genRandom(10);
        head.print();

        Node reverse = swapPair2(head);
        System.out.println();
        reverse.print();

    }

    private static Node pairSwap(Node Node) {
        if (Node == null || Node.next == null) {
            return null;
        }

        Node cur = Node;
        Node head = Node;
        Node pre = new Node(0, Node);
        int i = 1;

        while (cur != null && cur.next != null) {
            Node t2 = cur.next;
            Node t3 = cur.next.next;

            t2.next = cur;
            cur.next = t3;

            if (i++ == 1) {
                head = t2;
            }

            pre.next = t2;
            pre = cur;

            cur = t3;
        }


        return head;
    }

    public static Node swapPair2(Node Node) {
        if (Node == null || Node.next == null) {
            return Node;
        }

        Node pre = new Node(0, Node);
        Node tmp = pre;

        while (tmp.next != null && tmp.next.next != null) {
            Node t1 = tmp.next;
            Node t2 = t1.next;
            Node t3 = t2.next;

            t2.next = t1;
            t1.next = t3;

            tmp.next = t2;
            tmp = t1;
        }

        return pre.next;
    }

}
