package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aaron.zou
 * @date 2023/4/23 8:03 PM
 * <p>
 * 剑指 Offer II 079. 所有子集
 * 给定一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * <p>
 * <p>
 * 注意：本题与主站 78 题相同： https://leetcode-cn.com/problems/subsets/
 */
@UtilityClass
public class Off79M {

    public static void main(String[] args) {

    }

    List<List<Integer>> ret = new ArrayList<>();
    List<Integer> cur = new ArrayList<>();

    public List<List<Integer>> subsets2(int[] nums) {
        // 解法2； dfs；
        // 根据解法1 的分析，一直最终的总个数 为  2 ^ n 个，而问题的关键在于，找出每个子集所表示的
        // 具体的 组合
        // 对于每个位置，都有一种选择，  取 或者 不取，  假设在当前位置已经确定的情况，继续
        // 往下面走，则下一个位置同样没面临这样的情况， 取 或者 不取.  所以可用递归实现
        // 在每一个递归过程中，使用 cur 记录当前的子集，当走到最后一个元素的时候 结算一次，
        // 表示这个子集已经 唯一确定。 是一个答案.

        dfs(nums, 0, new ArrayList<>());

        return ret;
    }

    private void dfs(int[] nums, int curIdx, List<Integer> cur) {
        if (curIdx == nums.length) {
            // 到最后一位了，直接结算一次，这个 子集被唯一确定了.
            ret.add(cur);
            return;
        }

        // 当前面临两个选择，取或者不取.
        // 1. 取当前数字.
        cur.add(nums[curIdx]);
        dfs(nums, curIdx + 1, cur);
        // 取完以后，还有一种情况 不取，所以要把之前取的干掉.
        cur.remove(curIdx);

        // 2. 不取当前数字，所以不用加到 cur 里面.
        dfs(nums, curIdx + 1, cur);
    }

    public List<List<Integer>> subsets(int[] nums) {
        // 解法；利用 2 进制计数;
        // 数组中每个位置  在与不在  都可以用某个 二进制为 1 or 0 表示.
        // 比如  {1, 5, 9}  三个数字的数组， 可以用  三个二进制表示. x x x
        // 1在子集中，则用 1 表示，否则 0 表示.
        // 则有
        // 0 0 0   {}   0
        // 0 0 1   {9}  1
        // 0 1 0   {5}  2
        // 0 1 1   {5, 9}   3
        // 1 0 0   {1}   4
        // 1 0 1   {1, 9} 5
        // 1 1 0   {1, 5}  6
        // 1 1 1   {1, 5, 9}  7

        // 可以发现，子集的个数等于 数组中的 长度的 二次方  n ^ 2   2 的 三次方  2 ^ 3    1 << 3
        // 接下来就要找出具体的子集.  用每个具体的数  &  上该位置的 1，如果 = 1，说明这个位置在子集中.

        int len = nums.length;
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < (1 << len); i++) {

            // 检查 i 这个数字所代表的  二进制 0 0 0 是由哪几个位置组出来的，加到结果中.
            List<Integer> cur = new ArrayList<>();

            // 1 5 9
            // j 代表的是 第几位；
            for (int j = 0; j < len; j++) {
                // i的第几位不为0 则说明这个位代表的 数字在子集中.
                if ((i & (1 << j)) != 0) {
                    // i 左移几位 不为0，说明 这位 就构成子集.
                    cur.add(nums[j]);
                }
            }

            ret.add(cur);
        }

        return ret;
    }
}
