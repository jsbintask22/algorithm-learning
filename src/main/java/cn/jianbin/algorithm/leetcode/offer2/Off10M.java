package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jianbin.
 * @date 2023/4/2 18:03
 * <p>
 * 剑指 Offer II 010. 和为 k 的子数组
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2
 * 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
 * 示例 2：
 * <p>
 * 输入:nums = [1,2,3], k = 3
 * 输出: 2
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
@UtilityClass
public class Off10M {
    public static void main(String[] args) {
        System.out.println(solution(Utils.arr("1,1,1"), 2));

        System.out.println(solution(Utils.arr("1,2,3"), 3));

        System.out.println(solution2(Utils.arr("1,2,3"), 3));

    }

    public int solution2(int[] arr, int target) {
        // 优化，用一个 map存储某个 和 出现的次数；  比如 map(3) = 5 表示 和为 3 出现过5次；
        // 则 i 位置出现 和为 k 的次数为  map[pre[i] - k] 的次数；
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);

        int start = 0;
        int length = arr.length;
        int ret = 0;

        int pre = 0;
        while (start < length) {
            pre += arr[start++];
            ret += count.getOrDefault(pre - target, 0);

            count.put(pre, count.getOrDefault(pre, 0) + 1);
        }

        return ret;
    }

    public int solution(int[] arr, int target) {
        // 暴力枚举，两个指针一直往前面移动；
        int length = arr.length;
        int start = 0;
        int ret = 0;

        // 变量 sum 存储 START - END 之间的和
        while (start < length) {

            // 如果 sum 还没到 target，就要一直往后面加（可能有负数）
            int curEnd = start;
            int sum = 0;
            while (curEnd < length) {
                sum += arr[curEnd++];

                // 满足一个就要加一个，可能后面都是 0
                if (sum == target) {
                    ret++;
                }
            }

            start++;
        }

        return ret;
    }
}
