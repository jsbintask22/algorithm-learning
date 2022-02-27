package cn.jianbin.alogthrim.datastructure;

/**
 * @author aaron.zou
 * @date 2022/1/12 7:26 下午
 *
 * 队列；  先进先出
 */
public class LDeque {
    private Node head;
    private Node tail;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *  压入
     */
    public void offer(int val) {
        Node newNode = new Node(val, null);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    /**
     *  弹出
     */
    public Node poll() {
        if (size == 0) {
            return null;
        }

        Node ret = head;
        head = head.next;
        size--;
        return ret;
    }

    public Node take() {
        if (size == 0) {
            return null;
        }

        return head;
    }
}
