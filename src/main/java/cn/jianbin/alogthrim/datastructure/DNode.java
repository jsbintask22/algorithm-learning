package cn.jianbin.alogthrim.datastructure;

import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author aaron.zou
 * @date 2021/11/6 1:39 下午
 *
 * 双链表
 */
@Data
@AllArgsConstructor
public class DNode {
    private int val;
    public DNode next;
    public DNode pre;


    public void print() {
        DNode cur = this;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();

    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public static DNode genRandom(int len) {
        if (len == 0) {
            return null;
        }

        DNode dNode = new DNode(RandomUtil.randomInt(0, 10), null, null);
        DNode pre = null;
        DNode head = dNode;
        for (int i = 1; i < len; i++) {
            dNode.next = new DNode(RandomUtil.randomInt(0, 10), null, null);
            dNode.pre = pre;
            dNode.next.pre = dNode;

            pre = dNode;
            dNode = dNode.next;
        }

        return head;
    }



    public static void main(String[] args) {
        DNode Node = genRandom(10);
        Node.print();
    }
}
