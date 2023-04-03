package cn.jianbin.algorithm.linklist;

import cn.hutool.core.util.RandomUtil;
import cn.jianbin.alogthrim.datastructure.Node;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MergerLinkList {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Node h1 = Node.genOrdered(RandomUtil.randomInt(1, 6));
            Node h2 = Node.genOrdered(RandomUtil.randomInt(1, 6));
            h1.print();
            h2.print();
            solution(h1, h2).print();

            System.out.println("-----------------------------------");
        }
    }

    public Node solution(Node h1, Node h2) {
        if (h1 == null) {
            return h2;
        }

        if (h2 == null) {
            return h1;
        }


        Node point1 = h1;
        Node point2 = h2;

        Node pre = new Node(-1, null);
        Node ret = pre;

        while (point1 != null && point2 != null) {
            if (point1.getVal() < point2.getVal()) {
                pre.next = new Node(point1.getVal(), null);
                point1 = point1.next;
            } else {
                pre.next = new Node(point2.getVal(), null);
                point2 = point2.next;
            }

            pre = pre.next;
        }

        pre.next = point1 == null ? point2 : point1;

        return ret.next;
    }
}
