package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.LinkedList;

/**
 * @author jianbin.
 * @date 2023/4/9 20:18
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
