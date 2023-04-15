package cn.jianbin.algorithm.heap;

import cn.jianbin.alogthrim.datastructure.LHeap;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class HeapTest {

    public static void main(String[] args) {
        LHeap lHeap = new LHeap(10, false);

        IntStream.of(1, 2, 3, 5, 8, 1, 10, 0, -1, 21).forEach(lHeap::push);

        lHeap.print();

        while (!lHeap.isEmpty()) {
            System.out.println(lHeap.pop());
            lHeap.print();
        }
        System.out.println("------------------------------");

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        IntStream.of(1, 2, 3, 5, 8, 1, 10, 0, -1, 21).forEach(priorityQueue::offer);

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.peek());
            System.out.println(priorityQueue.poll());
        }
    }
}
