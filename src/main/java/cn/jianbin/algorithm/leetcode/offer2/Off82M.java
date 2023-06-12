package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author aaron.zou
 * @date 2023/4/24 7:32 PM
 * <p>
 * 剑指 Offer II 082. 含有重复元素集合的组合
 * 给定一个可能有重复数字的整数数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= candidates.length <= 100
 * 1 <= candidates[i] <= 50
 * 1 <= target <= 30
 * <p>
 * <p>
 * 注意：本题与主站 40 题相同： https://leetcode-cn.com/problems/combination-sum-ii/
 */
@UtilityClass
public class Off82M {

    public static void main(String[] args) {
        System.out.println(combinationSum2(Utils.arr("1,2,1,2,2,2,4"), 4));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);

        dfs(candidates, 0, target, new ArrayList<>());
        return ret;
    }

    List<List<Integer>> ret = new ArrayList<>();

    public void dfs(int[] arr, int curIdx, int remained, List<Integer> curPath) {
        // 解法； 选择当前或者 不选择 当前； 但是只能一直往下面走.
        if (remained == 0) {
            // 满足条件，直接退出.
            ret.add(new ArrayList<>(curPath));
            return;
        }

        if (curIdx >= arr.length) {
            // 没有数字选了。 只能退出.
            return;
        }

        // 去重常用手段； 用for 来代替另一种选择（前提是先排好序了）， 用 arr[i] == arr[i - 1] 来检查是否重复
        // 不是按顺序的一个一个选择可以用这种方式.
        for (int i = curIdx; i < arr.length; i ++) {
            // 已经排好序了； 所以剩下的如果小于 当前值就没必要走了.
            if (arr[i] > remained) {
                break;
            }

            // 比如 1 1 3  那么 第二个 1 是没有意义的，所有直接下一个.
            if (i > curIdx && arr[i] == arr[i - 1]) {
                continue;
            }

            // 没有没有不选择的 选项，因为 当 i++ 以后，就相当于是不选择了;
            curPath.add(arr[i]);
            dfs(arr, i + 1, remained - arr[i], curPath);
            curPath.remove(curPath.size() - 1);
        }

    }


}
