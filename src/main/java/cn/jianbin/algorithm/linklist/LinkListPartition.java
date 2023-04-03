package cn.jianbin.algorithm.linklist;

import cn.hutool.core.util.RandomUtil;
import cn.jianbin.alogthrim.datastructure.Node;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LinkListPartition {
    public static void main(String[] args) {
        Node root = Node.genRandom(10);
        root.print();
        int p = RandomUtil.randomInt(0, 10);
        System.out.println("partition is " + p);
        Node partition = partition(root, p);
        partition.print();
    }

    public Node partition(Node head, int partition) {
        // 遍历链表，将不同大小的 值分别指向不同引用
        if (head == null || head.next == null) {
            return head;
        }

        Node cur = head;
        Node ls = null;
        Node le = null;
        Node es = null;
        Node ee = null;
        Node ms = null;
        Node me = null;

        while (cur != null) {
            int val = cur.getVal();
            if (val < partition) {
                if (ls == null) {
                    ls = le = cur;
                } else {
                    le.next = cur;
                    le = cur;
                }
            }

            if (val == partition) {
                if (es == null) {
                    es = ee = cur;
                } else {
                    ee.next = cur;
                    ee = cur;
                }
            }

            if (val > partition) {
                if (ms == null) {
                    ms = me = cur;
                } else {
                    me.next = cur;
                    me = cur;
                }
            }

            cur = cur.next;
        }

        // 把每个引用的 next 置空
        if (le != null) {
            le.next = null;
        }
        if (ee != null) {
            ee.next = null;
        }

        if (me != null) {
            me.next = null;
        }

        // 重连引用
        // 1 4 5 1 3 10 14 2 5 9 20
        // partition = 5
        // 1 4 1 3 2
        // 5 5
        // 10 14 9 20

        if (es == null && ms == null) {
            return ls;
        }

        if (ls == null && ms == null) {
            return es;
        }

        if (ls == null && es == null) {
            return ms;
        }

        if (le != null) {
            le.next = (es == null ? ms : es);
        }

        if (ee != null) {
            ee.next = ms;
        }

        return ls == null ? es : ls;
    }
}
