package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.PriorityQueue;

/**
 * @author aaron.zou
 * @date 2023/4/15 4:27 PM
 *
 * 剑指 Offer II 059. 数据流的第 K 大数值
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 *
 * 请实现 KthLargest 类：
 *
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 *
 *
 * 示例：
 *
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 *
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 *
 *
 * 提示：
 *
 * 1 <= k <= 104
 * 0 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * -104 <= val <= 104
 * 最多调用 add 方法 104 次
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 *
 *
 * 注意：本题与主站 703 题相同： https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/
 *
 * 通过
 */
@UtilityClass
public class Off59E {


    public static class KthLargest {

        // 解法；小根堆；
        // 原理： 一颗完全二叉树， 二叉树的父节点 大于 左右子节点，并且每颗子树都保持一样的特性；
        // 所以； 可以使用数组存储；
        // 加入 现有   1
        //        2      3
        //     4    5  6   7

        // 加入 现在数组 size = 7，  cur = 6   idx = 5
        // 则  父亲位置为   (5 - 1) / 2 = 2
        //  idx = n,  p = (n - 1) / 2    leftC = n * 2 = 1    rightC = n * 2 + 2;

        // 每次变动 都会保持这个特性，这就是小根堆；

        // 大根堆； 则跟上面相反， 父节点 大于 左右 子节点；


        // 所以此题；  求 数组中 第 k 大的元素；
        // 先把无序数组加到  小根堆中， 加完之后，判断是否已经 满了（k个），满了则干掉最后一个，
        // 则 返回的结果 必然是最后一个（因为已经排好序了），并且控制了 小根堆的大小只有 k个；

        int size;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        public KthLargest(int k, int[] nums) {
            size = k;

            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            priorityQueue.offer(val);

            if (priorityQueue.size() > size) {
                priorityQueue.poll();
            }

            return priorityQueue.peek();
        }
    }
}
