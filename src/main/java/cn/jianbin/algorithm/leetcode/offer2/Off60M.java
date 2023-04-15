package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author aaron.zou
 * @date 2023/4/15 4:40 PM
 * <p>
 * 剑指 Offer II 060. 出现频率最高的 k 个数字
 * 给定一个整数数组 nums 和一个整数 k ，请返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 * <p>
 * <p>
 * 进阶：所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 * <p>
 * <p>
 * <p>
 * 注意：本题与主站 347 题相同：https://leetcode-cn.com/problems/top-k-frequent-elements/
 */
@UtilityClass
public class Off60M {

    public static void main(String[] args) {
        Utils.printArr(topKFrequent(Utils.arr("1,1,1,2,2,3"), 2));
    }

    public int[] topKFrequent(int[] nums, int k) {
        // 解法； 加到 map<val，次数>  找出 次数排在前面的 数字；

        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>((e1, e2) -> {
            // 从大到小排；
            return e2.getValue() - e1.getValue();
        });

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 另外一个思路，小根堆， 如果满了 k 个，则出队（干掉最小的）
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            priorityQueue.offer(entry);
        }

        int[] ret = new int[k];

        for (int i = 0; i < k; i++) {
            ret[i] = priorityQueue.poll().getKey();
        }

        return ret;
    }
}
