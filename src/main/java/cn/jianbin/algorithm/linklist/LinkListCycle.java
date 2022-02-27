package cn.jianbin.algorithm.linklist;

import cn.jianbin.alogthrim.datastructure.Node;

import java.util.HashSet;
import java.util.Set;

/**
 * @author aaron.zou
 * @date 2021/11/14 2:53 下午
 */

public class LinkListCycle {
    public static void main(String[] args) {
        Node head = Node.genCycle();
        Node noCycle = Node.genRandom(100);
        noCycle.print();

        boolean b = hasCycle1(head);
        System.out.println(b);
        System.out.println(hasCycle1(noCycle));
    }

    public static boolean hasCycle1(Node head) {
        Set<Node> set = new HashSet<>();

        while (head != null) {
            if (!set.contains(head)) {
                set.add(head);
            } else {
                System.out.println(head);
                return false;
            }

            head = head.next;
        }

        return true;
    }

}
