package cn.jianbin.alogthrim.datastructure;

import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author aaron.zou
 * @date 2021/11/6 1:39 下午
 */
@Data
@AllArgsConstructor
public class Node {
    private int val;
    public Node next;


    public void print() {
        System.out.print(val);

        if (next != null) {
            System.out.print("->");
            next.print();
        } else {
            System.out.println();
        }
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static Node genRandom(int len) {
        if (len == 0) {
            return null;
        }

        Node Node = new Node(RandomUtil.randomInt(0, 10), null);
        Node head = Node;
        for (int i = 1; i < len; i++) {
            Node.next = new Node(RandomUtil.randomInt(0, 10), null);
            Node = Node.next;
        }

        return head;
    }

    public static Node genOrdered(int len) {
        if (len == 0) {
            return null;
        }

        Node cur = new Node(0, null);
        Node head = cur;
        for (int i = 1; i < len; i++) {
            do {
                cur.next = new Node(i, null);
            } while (cur.next.getVal() < cur.getVal());

            cur = cur.next;
        }

        return head;
    }

    public static Node gen(String nums) {
        List<Integer> list = Stream.of(nums.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Node pre = new Node(-1, null);
        Node root = pre;

        for (Integer integer : list) {
            pre.next = new Node(integer, null);
            pre = pre.next;
        }

        return root.next;
    }

    public static Node genCycle() {
        Node head = new Node(1, null);
        int i1 = RandomUtil.randomInt(10, 100);
        int cycleIdx = RandomUtil.randomInt(50, 90);
        Node tmp = head;
        Node cycleNode = null;
        for (int i = 0; i < i1; i++) {
            if (i1 - i < 2) {
                System.out.println("添加环");
                tmp.next = cycleNode;
            } else {
                tmp.next = new Node((i + 2), null);
            }

            if (i == cycleIdx) {
                cycleNode = tmp;
            }

            tmp = tmp.next;
        }

        return head;
    }


    public static void main(String[] args) {
        Node Node = genRandom(10);
        Node.print();
    }
}
