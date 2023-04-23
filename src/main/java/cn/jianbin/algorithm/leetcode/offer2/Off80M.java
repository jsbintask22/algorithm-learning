package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aaron.zou
 * @date 2023/4/23 8:49 PM
 * <p>
 * 剑指 Offer II 080. 含有 k 个元素的组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * 示例 2:
 * <p>
 * 输入: n = 1, k = 1
 * 输出: [[1]]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= n <= 20
 * 1 <= k <= n
 * <p>
 * <p>
 * 注意：本题与主站 77 题相同： https://leetcode-cn.com/problems/combinations/
 */
@UtilityClass
public class Off80M {

    public static void main(String[] args) {
        System.out.println(combine(3, 2));
    }

    List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        // 回溯； 对于每个 元素，都有 选 或者 不选
        // 如果当前元素选了； 那剩下的长度就还有  k - 1个，再往下面走
        // 如果当前预算没选； 那剩下的长度还有 k 个，继续往下面走
        // 走到 当前预算超过 n 了并且 k 的长度为 0，就找到了一个答案，否则没有找到.

        dfs(n, k, new ArrayList<>());

        return ret;
    }

    private void dfs(int cur, int remained, List<Integer> curPath) {

        if (remained == 0) {
            // n 到最后一个值了，并且满足了 恰好 k个，此时找到了一个解
            ret.add(new ArrayList<>(curPath));
            return;
        }


        // 当前元素选择完成，下一个预算 必须 大于等于 0 才让走
        if (cur >= 1) {
            // 1. 选择当前 元素，则剩下 remained - 1
            curPath.add(cur);

            dfs(cur - 1, remained - 1, curPath);
            // 选择完了以后，恢复现场.
            curPath.remove(curPath.size() - 1);

            // 2. 不选择当前元素，则生效 remained
            dfs(cur - 1, remained, curPath);
        }
    }
}
