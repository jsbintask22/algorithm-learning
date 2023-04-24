package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author aaron.zou
 * @date 2023/4/24 8:44 PM
 *
 * 剑指 Offer II 083. 没有重复元素集合的全排列
 * 给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列 。可以 按任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 *
 * 注意：本题与主站 46 题相同：https://leetcode-cn.com/problems/permutations/
 */
@UtilityClass
public class Off83M {

    public static void main(String[] args) {
        System.out.println(permute(Utils.arr("2,3,10")));
    }

    public List<List<Integer>> permute(int[] nums) {
        // 解法；每次从头结点开始，固定一个位置然后再去选择；
        // 初始时把所有下标放到 一个 池子中，表明这个下标还没被选择；
        // 每次从池子中取一个出来作为当前节点，一直到当前池子 被取空了;

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

            path.add(arr[i]);
            curChooseIdxs.add(i);
            dfs(arr, curChooseIdxs, path);
            path.remove(path.size() - 1);
            curChooseIdxs.remove(curChooseIdxs.size() - 1);
        }
    }

}
