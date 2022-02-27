package cn.jianbin.algorithm.linklist;

import cn.hutool.core.util.RandomUtil;
import cn.jianbin.alogthrim.datastructure.Node;
import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2022/1/12 8:38 下午
 *
 * 添加两个数到一个链表
 * 123 + 13 = 136
 *
 * 3 -> 1
 * 3 -> 2 -> 1
 *
 * 6 -> 3 -> 1
 */
@UtilityClass
public class AddTwoNumToLinkList {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Node h1 = Node.genRandom(RandomUtil.randomInt(1, 6));
            Node h2 = Node.genRandom(RandomUtil.randomInt(1, 6));

            h1.print();
            h2.print();

            solution(h1, h2).print();

            System.out.println("------------------------------------------");
        }
    }

    public Node solution(Node h1, Node h2) {
        if (h1 == null) {
            return h2;
        }

        if (h2 == null) {
            return h1;
        }

        int l1 = getLinkListLen(h1);
        int l2 = getLinkListLen(h2);


        Node l = l1 > l2 ? h1 : h2;
        Node s = l == h1 ? h2 : h1;

        Node ret = l;
        Node pre = l;
        int carry = 0;

        // 先遍历短的链表
        while (s != null) {
            int sVal = s.getVal();
            int lVal = l.getVal();


            int cVal = sVal + lVal + carry;

            carry = cVal / 10;
            l.setVal(cVal % 10);

            pre = l;
            s = s.next;
            l = l.next;
        }

        // 再来开始长的链表
        while (l  != null) {
            int lVal = l.getVal();

            int cVal = lVal + carry;

            carry = cVal / 10;
            l.setVal(cVal % 10);

            pre = l;
            l = l.next;
        }

        if (carry > 0) {
            pre.next = new Node(carry, null);
        }

        return ret;
    }

    public int getLinkListLen(Node head) {
        int ret = 0;
        while (head != null) {
            ret++;
            head = head.next;
        }

        return ret;
    }
}
