package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author aaron.zou
 * @date 2023/4/24 9:12 PM
 *
 * 剑指 Offer II 084. 含有重复元素集合的全排列
 * 给定一个可包含重复数字的整数集合 nums ，按任意顺序 返回它所有不重复的全排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * 示例 2：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 *
 * 注意：本题与主站 47 题相同： https://leetcode-cn.com/problems/permutations-ii/
 */
@UtilityClass
public class Off84M {

    public static void main(String[] args) {
        System.out.println(permuteUnique(Utils.arr("3,2,1,3,1")));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {

        // 解法；每次从头结点开始，固定一个位置然后再去选择；
        // 初始时把所有下标放到 一个 池子中，表明这个下标还没被选择；
        // 每次从池子中取一个出来作为当前节点，一直到当前池子 被取空了;
        // 值得注意的是，这里元素可能重复了，
        // 为了去重，先给 元素 排序，再进行选择，如果
        // 当前元素和前面一个，那这个节点就没有再递归一次了，因为肯定已经包含在
        // 前面的子集里面了（交换后一样）

        Arrays.sort(nums);
        dfs(nums, new ArrayList<>(), new ArrayList<>());
        return ret;
    }

    List<List<Integer>> ret = new ArrayList<>();

    public void dfs(int[] arr, List<Integer> curChooseIdxs, List<Integer> path) {
        // 退出条件， 池子中的 下标位置被取光了；
        if (curChooseIdxs.size() == arr.length) {
            ret.add(new ArrayList<>(path));
            return;
        }

        // 池子中的坐标还没被取光. 那每一个位置都有可能作为当前节点.
        for (int i = 0; i < arr.length; i++) {
            // 如果当前 下标 还没被选择过. 选择它.
            if (curChooseIdxs.contains(i)) {
                continue;
            }

            if (i > 0 && arr[i] == arr[i - 1] && curChooseIdxs.contains(i - 1)) {
                continue;
            }

            path.add(arr[i]);
            curChooseIdxs.add(i);
            dfs(arr, curChooseIdxs, path);
            path.remove(path.size() - 1);
            curChooseIdxs.remove(curChooseIdxs.size() - 1);
        }
    }

}
