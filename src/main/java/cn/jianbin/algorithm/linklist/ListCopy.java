package cn.jianbin.algorithm.linklist;

import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2022/2/10 7:18 下午
 *
 * 深度克隆 Node 链表
 */
@UtilityClass
public class ListCopy {
    static class Node {
        int val;
        Node next;
        Node rand;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

    }

    public Node copy(Node head) {
        if (head == null) {
            return null;
        }

        // 方法1：hash map 先遍历一遍存储所有节点； key: 原先的节点  val：克隆的节点
        // 第二次再次遍历 head；连接所有 next 和 rand

        // 方法2：遍历 head；  将所有 克隆节点插入到  当前节点的下一个节点；
        // 再次遍历； next 和 rand 都能找得到

        return null;
    }

}
