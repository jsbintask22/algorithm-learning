package cn.jianbin.algorithm.heap;

import cn.jianbin.alogthrim.datastructure.LHeap;

import java.util.stream.IntStream;

public class HeapTest {

    public static void main(String[] args) {
        LHeap lHeap = new LHeap(10, true);

        IntStream.of(1, 2, 3, 5, 8, 1, 10, 0, -1, 21).forEach(lHeap::push);

        lHeap.print();

        while (!lHeap.isEmpty()) {
            System.out.println(lHeap.pop());
            lHeap.print();
        }
    }
}
