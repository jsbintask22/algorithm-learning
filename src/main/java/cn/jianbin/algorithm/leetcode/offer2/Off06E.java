package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jianbin.
 * @date 2023/4/1 22:24
 * <p>
 * <p>
 * 剑指 Offer II 006. 排序数组中两个数字之和
 * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 * <p>
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 0 开始计数 ，所以答案数组应当满足 0 <= answer[0] < answer[1] < numbers.length 。
 * <p>
 * 假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：numbers = [1,2,4,6,10], target = 8
 * 输出：[1,3]
 * 解释：2 与 6 之和等于目标数 8 。因此 index1 = 1, index2 = 3 。
 * 示例 2：
 * <p>
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[0,2]
 * 示例 3：
 * <p>
 * 输入：numbers = [-1,0], target = -1
 * 输出：[0,1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 递增顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 */
public class Off06E {

    public static void main(String[] args) {
        // TODO: aaron.zou 2023/4/1 优化： 使用二分查找，可以优化成 o(nlog(n) )  利用好 数组是有序的这个特点；

        Utils.printArr(solution(Utils.arr2("123456"), 11));
        Utils.printArr(solution2(Utils.arr2("123456"), 11));

        Utils.printArr(solution2(Utils.arr2("176"), 7));
    }

    public static int[] solution2(int[] arr, int target) {
        // 二分查找法；利用数组有序特点；
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int cur = arr[low] + arr[high];
            if (cur == target) {
                return new int[]{low, high};
            }

            if (cur < target) {
                // 小于目标值，往右移动；
                low = low + (high - low) / 2;
            } else {
                high = low + (high - low) / 2;
            }
        }

        return new int[]{-1, -1};
    }

    public static int[] solution(int[] arr, int target) {
        Map<Integer, Integer> points = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];
            if (points.containsKey(target - cur)) {
                return new int[]{points.get(target - cur), i};
            }
            points.put(cur, i);
        }

        return new int[]{-1};
    }
}
