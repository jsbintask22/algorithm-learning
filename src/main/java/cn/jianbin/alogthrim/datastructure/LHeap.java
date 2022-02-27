package cn.jianbin.alogthrim.datastructure;

import cn.jianbin.algorithm.utils.Utils;
import lombok.ToString;

/**
 * @author aaron.zou
 * @date 2022/2/6 4:49 下午
 * 堆结构
 */
public class LHeap {
    private final int[] data;
    private int heapSize;
    private final int sizeLimit;

    /**
     * 是否大根堆
     */
    private final boolean isBigHeap;

    public LHeap(int limit, boolean isBigHeap) {
        if (limit < 0) {
            throw new IllegalArgumentException("heap size must more than 0;");
        }
        this.sizeLimit = limit;
        this.isBigHeap = isBigHeap;
        heapSize = 0;
        data = new int[limit];
    }

    /**
     *  入堆
     */
    public void push(int val) {
        if (heapSize + 1 > sizeLimit) {
            throw new RuntimeException("heap is full.");
        }

        // 先将 val 放到最后的位置；
        data[heapSize] = val;

        // 再比较 val 和父节点的值
        doPush(data, isBigHeap, heapSize++);
    }

    /**
     *  弹出堆的最大值，并且重新构建堆
     */
    public int pop() {
        if (heapSize == 0) {
            throw new RuntimeException("heap have no data.");
        }

        int top = data[0];
        // 用最后的值替换掉 top 的位置
        data[0] = data[--heapSize];
        heapify(data, 0, heapSize - 1, isBigHeap);

        return top;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    private void heapify(int[] data, int index, int heapSize, boolean isBigHeap) {
        // 找到左节点
        int left = index * 2 + 1;
        if (left > heapSize) {
            return;
        }

        // 如果有左节点 OR 右节点；   并且子节点有大于 index 的值
        int largest = (left + 1 > heapSize ? left : (data[left] > data[left + 1] ? left : left + 1));

        // 判断 largest 的值是否 大于 index 的值
        if (isBigHeap) {
            if (data[largest] > data[index]) {
                swap(data, largest, index);

                // 交换后递归
                heapify(data, largest, heapSize, isBigHeap);
            }
        } else {
            if (data[largest] < data[index]) {
                swap(data, largest, index);

                // 交换后递归
                heapify(data, largest, heapSize, isBigHeap);
            }
        }
    }


    private void doPush(int[] data, boolean isBigHeap, int index) {
        // 比较 index 位置和 父节点的大小
        int parent = (index - 1) / 2;

        if (isBigHeap) {
            while (data[index] > data[parent]) {
                // 如果新进来的节点 大于 父节点; 交换
                swap(data, index, parent);
                index = parent;
                parent = (index - 1) / 2;
            }
        } else {
            while (data[index] < data[parent]) {
                // 如果新进来的节点 大于 父节点; 交换
                swap(data, index, parent);
                index = parent;
                parent = (index - 1) / 2;
            }
        }
    }

    private void swap(int[] data, int i1, int i2) {
        int temp = data[i1] ^ data[i2];
        data[i1] = temp ^ data[i1];
        data[i2] = temp ^ data[i1];
    }

    public void print() {
        for (int i = 0; i < heapSize; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}
