package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author aaron.zou
 * @date 2023/4/3 3:10 PM
 * <p>
 * 剑指 Offer II 011. 0 和 1 个数相同的子数组
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * 示例 2：
 * <p>
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 */
@UtilityClass
public class Off11M {

    public static void main(String[] args) {
        System.out.println(solution(Utils.arr("0,1,1,0,1,0")));

        System.out.println(solution(Utils.arr("0,0,0,1,1,1,0")));

        System.out.println(solution2(Utils.arr("0,0,0,1,1,1,0")));

    }

    public int solution2(int[] arr) {
        // 0,0,0,1,1,1,0；
        // 构建一个前缀和 数组； pre
        // -1,-2,-3,-2,-1,0,-1
        // （-1，0），（-2，1），（-3，2）

        Map<Integer, Integer> preMap = new HashMap<>();
        preMap.put(0, -1);

        int preSum = 0;
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            // 01011
            // -1 0 -1 0 1
            preSum += arr[i] == 0 ? -1 : 1;

            // 检查 presum 是否已经在map中出现过了；
            if (preMap.containsKey(preSum)) {
                maxLen = Math.max(maxLen, i - preMap.get(preSum));
            } else {
                preMap.put(preSum, i);
            }
        }

        return maxLen;
    }

        public int solution(int[] arr) {
        // 这里跟题目不一样，这里是找 相同数量 0 和 1 并且 0 和 1 必须挨着；
        // 两个指针，一直往后面移动 判断当前 是否与前面不一样； 则可以加入；
        // 遇到一样的停止，并且判断当前 已累计的是 奇数还是偶数；
        int start = 0;
        int end = 1;

        int max = 0;
        int len = arr.length;
        while (end < len) {
            if (arr[end] == arr[end - 1]) {
                // 一样，到这里终止；
                int c = end - start;
                max =  Math.max(c % 2 == 0 ? c : c - 1, max);
                start = end;
            }

            end++;
        }

        int c = end - start;
        max =  Math.max(c % 2 == 0 ? c : c - 1, max);


        return max;
    }
}
