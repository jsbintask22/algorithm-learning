package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;

/**
 * @author jianbin.
 * @date 2023/4/1 19:12
 *
 *
 *
 * 剑指 Offer II 004. 只出现一次的数字
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 *
 * 输入：nums = [0,1,0,1,0,1,100]
 * 输出：100
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
 *
 *
 * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
public class Off04M {

    public static void main(String[] args) {
        System.out.println(solution(Utils.arr2("1112223555000")));
    }

    public static int solution(int[] arr) {
        // 用一个 二维数组 arr[][]  一维表示 数值 i，二维表示 出现的次数；
        int[][] ret = new int[maxVal(arr) + 1][3 + 1];

        for (int i = 0; i < arr.length; i++) {
            int[] counts = ret[arr[i]];
            for (int i1 = 1; i1 < counts.length; i1++) {
                if (counts[i1] == 0) {
                    counts[i1] = 1;
                    break;
                }
            }
        }

        for (int i = 0; i < ret.length; i++) {
            int[] counts = ret[i];
            if (counts[1] == 1 && counts[2] == 0) {
                return i;
            }
        }

        return -1;
    }

    public static int maxVal(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        return max;
    }
}
