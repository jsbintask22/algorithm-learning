package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aaron.zou
 * @date 2023/4/24 7:05 PM
 *
 * 剑指 Offer II 081. 允许重复选择元素的组合
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 *
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 *
 *
 *
 * 示例 1：
 *
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 * 示例 2：
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 *
 * 输入: candidates = [2], target = 1
 * 输出: []
 * 示例 4：
 *
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * 示例 5：
 *
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 *
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 *
 * 注意：本题与主站 39 题相同： https://leetcode-cn.com/problems/combination-sum/
 */
@UtilityClass
public class Off81M {

    public static void main(String[] args) {
        System.out.println(combinationSum(Utils.arr("2,4"), 4));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // 解法； 回溯，每次选择当前 或者 不选择当前;
        // 终止； 数组到了 OR target 到了 OR 大于 target 了

        dfs(candidates, 0, 0, target, new ArrayList<>());

        return ret;
    }

    List<List<Integer>> ret = new ArrayList<>();
    public void dfs(int[] arr, int curIdx, int curSum, int target, List<Integer> path) {
        // 边界条件； 如果 target 满了，退回（因为都是正整数，不用往下面加了)
        if (target == curSum) {
            ret.add(new ArrayList<>(path));
            return;
        }

        // 如果 curSum > target, 说明已经加过头了，退出.
        if (curSum > target) {
            return;
        }

        // 如果已经没有多余的数字了，退回.
        if (curIdx >= arr.length) {
            return;
        }

        // 到这里，开始递归内容；
        // 1. 选择当前内容. 并且下一个继续抉择走当前内容
        path.add(arr[curIdx]);
        dfs(arr, curIdx, curSum + arr[curIdx], target, path);
        path.remove(path.size() - 1);


        // 2. 不选择当前内容. 走到下一个.
        dfs(arr, curIdx + 1, curSum, target, path);
    }
}
