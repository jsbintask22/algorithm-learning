package cn.jianbin.algorithm.leetcode.practise;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/4/3 5:34 PM
 * <p>
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * <p>
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * 示例 2：
 * <p>
 * 输入：nums = [5], k = 1
 * 输出：5.00000
 *  
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= k <= n <= 105
 * -104 <= nums[i] <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-average-subarray-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@UtilityClass
public class P643E {
    public static void main(String[] args) {
        System.out.println(solution(Utils.arr("[1,12,-5,-6,50,3]"), 4));

    }

    public float solution(int[] arr, int k) {
        // 解决方案；1：滑动窗口
        // 2: 前缀和数据，统计最大值；

        // len = 5  pre.len = 6  k = 3(5, 4, 3)
        int[] pre = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            pre[i + 1] = pre[i] + arr[i];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < pre.length - k; i++) {
            max = Math.max(max, pre[i + k] - pre[i]);
        }

        return max * 1f / k;
    }
}
