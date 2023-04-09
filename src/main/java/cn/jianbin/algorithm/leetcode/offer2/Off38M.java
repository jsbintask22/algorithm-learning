package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.LinkedList;

/**
 * @author jianbin.
 * @date 2023/4/9 15:23
 *
 * 剑指 Offer II 038. 每日温度
 * 请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 *
 *
 * 示例 1:
 *
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 *
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 *
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 *
 * 提示：
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 *
 *
 * 注意：本题与主站 739 题相同： https://leetcode-cn.com/problems/daily-temperatures/
 *
 * 通过次数34,977提交次数45,806
 */
@UtilityClass
public class Off38M {
    public static void main(String[] args) {
        Utils.printArr(solution(Utils.arr("73,74,75,71,69,72,76,73")));

        Utils.printArr(solution2(Utils.arr("73,74,75,71,69,72,76,73")));

    }

    public int[] solution2(int[] temperatures) {
        // 解法2：单调栈；
        // 从 顶 -> 低  一次递增，递增栈，否则递减栈；
        // 新增一个 递增栈 用来存储还没出结果的 索引idx， 如果发现后面有 元素 大于，则一直出栈；

        LinkedList<Integer> stack = new LinkedList<>();
        int[] ret = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            int cur = temperatures[i];

            while (!stack.isEmpty() && temperatures[stack.peek()] < cur) {
                Integer top = stack.pop();
                ret[top] = i - top;
            }

            stack.push(i);
        }

        return ret;
    }

        public int[] solution(int[] temperatures) {
        // 输入: [73,74,75,71,69,72,76,73]
        // 输出: [1,1,4,2,1,1,0,0]

        int[] ret = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            int j = i + 1;
            for (; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    break;
                }
            }
            ret[i] = j < temperatures.length ? j - i : 0;
        }

        return ret;
    }
}
