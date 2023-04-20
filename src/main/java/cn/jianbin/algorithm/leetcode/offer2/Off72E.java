package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/4/19 8:54 PM
 * <p>
 * 剑指 Offer II 072. 求平方根
 * 给定一个非负整数 x ，计算并返回 x 的平方根，即实现 int sqrt(int x) 函数。
 * <p>
 * 正数的平方根有两个，只输出其中的正数平方根。
 * <p>
 * 如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: x = 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: x = 8
 * 输出: 2
 * 解释: 8 的平方根是 2.82842...，由于小数部分将被舍去，所以返回 2
 * <p>
 * <p>
 * 提示:
 * <p>
 * 0 <= x <= 231 - 1
 * <p>
 * <p>
 * 注意：本题与主站 69 题相同： https://leetcode-cn.com/problems/sqrtx/
 */
@UtilityClass
public class Off72E {
    public static void main(String[] args) {

    }

    public int mySqrt2(int x) {
        int left = 0;
        int right = x;

        while (left <= right) {
            int mid = left + (right - left >> 1);

            long val = (long) mid * mid;

            if (val <= x) {
                // 满足条件. 尝试继续放大；
                left = mid + 1;
            } else {
                // 不满足； val > x; 尝试放小.
                right = mid - 1;
            }
        }

        // right 是最后一个满足 val <= x 的数，
        // left 是第一个 满足 val > x 的数，
        // 返回 right;
        return right;
    }


    public int mySqrt(int x) {
        // 解法，二分；
        // 一个数 x 的整数平方根， 总是 从 1 开始 到 x 这个区间内，
        // 这是一个递增的过程，可以使用 二分查找。
        // 找出 某个 数  mid 的平台 <= x 但是 mid + 1 > x 这个变幻过程.

        int left = 0;
        int right = x;

        int ret = 0;
        while (left <= right) {
            int mid = left + (right - left >> 1);

            long val = (long) mid * mid;
            if (val == x) {
                // 找到了，直接返回.
                return mid;
            }
            // 25 > 10
            if (val > x) {
                right = mid - 1;
            } else {
                // 此时的 left 是一个可能的解
                ret = mid;
                left = mid + 1;
            }
        }

        return right;
    }
}
