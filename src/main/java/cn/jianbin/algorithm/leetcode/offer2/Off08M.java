package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/2 14:10
 * <p>
 * 剑指 Offer II 008. 和大于等于 target 的最短子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
@UtilityClass
public class Off08M {

    public static void main(String[] args) {
        // System.out.println(solution(Utils.arr("2,3,1,2,1,3"), 7));
        System.out.println(solution2(Utils.arr("1,2,3,4,5"), 11));

    }

    public int solution(int[] arr, int target) {
        int start = 0;
        int end = 0;
        int sum = 0;
        int ret = Integer.MAX_VALUE;

        while (end < arr.length) {
            sum += arr[end];

            while (sum >= target) {
                ret = Math.min(ret, end - start + 1);
                sum -= arr[start];
                start++;
            }

            end++;
        }

        return ret == Integer.MAX_VALUE ? 0 : ret;
    }


    public int solution2(int[] arr, int target) {
        int start = 0;
        int end = 0;
        int len = Integer.MAX_VALUE;
        int n = arr.length;

        while (start < n && end < n) {
            int sum = 0;

            int curStart = start;
            while (sum < target && curStart < n) {
                sum += arr[curStart];
                curStart++;
            }

            if (sum >= target) {
                len = Math.min(len, (curStart - start));
                end = Math.max(curStart - 1, end);
            }

            start++;
        }

        return len == Integer.MAX_VALUE ? 0 : len;
    }

}
