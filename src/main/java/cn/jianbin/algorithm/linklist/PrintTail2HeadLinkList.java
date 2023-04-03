package cn.jianbin.algorithm.linklist;

import cn.jianbin.algorithm.utils.Utils;
import cn.jianbin.alogthrim.datastructure.Node;
import lombok.experimental.UtilityClass;

import java.util.Stack;

@UtilityClass
public class PrintTail2HeadLinkList {
    public static void main(String[] args) {
        Node head = Node.genRandom(10);
        head.print();

        int[] ints = printWithStack(head);
        Utils.printArr(ints);
    }

    public int[] printWithStack(Node head) {
        if (head == null) {
            return null;
        }

        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.getVal());
            head = head.next;
        }

        int size = stack.size();
        int[] ret = new int[size];

        for (int i = 0; i < size; i++) {
            ret[i] = stack.pop();
        }

        return ret;
    }
}
