package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.LinkedList;

/**
 * @author jianbin.
 * @date 2023/4/9 20:18
 *
 * 剑指 Offer II 039. 直方图最大矩形面积
 * 给定非负整数数组 heights ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 示例 2：
 *
 *
 *
 * 输入： heights = [2,4]
 * 输出： 4
 *
 *
 * 提示：
 *
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 *
 *
 * 注意：本题与主站 84 题相同： https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 */
@UtilityClass
public class Off39H {

    public static void main(String[] args) {
        System.out.println(solution(Utils.arr("5,4,1,3")));

        System.out.println(solution(Utils.arr("2,1,2")));

        System.out.println(solution(Utils.arr("2,1,5,6,2,3")));


    }

    public int solution(int[] heights) {
        // 解法：最大面积是指定一个中间节点后往两边扩散，当扩散不了（必须是长方形）的时候，计算面积，然后
        // 继续下一个中心节点； 此为 暴力遍历法
        // 利用单调栈 的特性，知道遇到比前面一个 小的元素，这个时候才开始 计算宽度；

        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(-1);

        int len = heights.length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
                // 当前高度比前面高度矮，计算高度；
                Integer top = stack.pop();
                max = Math.max(max, heights[top] * (i - stack.peek() - 1));
            }

            stack.push(i);
        }

        // stack 中还剩一些没计算大小；
        while (stack.peek() != -1) {
            Integer top = stack.pop();
            max = Math.max(max, heights[top] * (len - stack.peek() - 1));
        }

        return max;
    }
}
