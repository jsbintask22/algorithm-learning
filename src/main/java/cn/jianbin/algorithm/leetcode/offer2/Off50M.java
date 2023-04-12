package cn.jianbin.algorithm.leetcode.offer2;

import cn.hutool.extra.spring.SpringUtil;
import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author aaron.zou
 * @date 2023/4/12 8:11 PM
 *
 *剑指 Offer II 050. 向下的路径节点之和
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 *
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 *
 *
 * 注意：本题与主站 437 题相同：https://leetcode-cn.com/problems/path-sum-iii/
 */
@UtilityClass
public class Off50M {

    public static void main(String[] args) {

    }

    public int pathSum(TreeNode root, int targetSum) {
        // 解法； 1，dfs 深度遍历枚举每一个可能性；
        // 2； 前缀和 + dfs；
        // 定一个一个 前缀和 map， 记录 root - cur 节点上的和，以及出现的次数；
        // 另外用一个 变量 curval 记录 从 root - cur（exclude) 的和，检查
        // 是否存在一个 前缀和 = curVal - targetSum  这样的前缀和，如果有，返回个数；
        Map<Long, Integer> preMap = new HashMap<>();
        preMap.put(0L, 1);

        return solution(preMap, 0, targetSum, root);
    }

    public int solution(Map<Long, Integer> preMap, long curVal, int targetSum, TreeNode root) {
        if (root == null) {
            // 如果当前节点为空了，不用考虑了，不会到这里来；
            return 0;
        }

        curVal += root.val;

        Integer paths = preMap.getOrDefault(curVal - targetSum, 0);
        preMap.put(curVal, preMap.getOrDefault(curVal, 0) + 1);
        paths += solution(preMap, curVal, targetSum, root.left);
        paths += solution(preMap, curVal, targetSum, root.right);
        preMap.put(curVal, preMap.getOrDefault(curVal, 0) - 1);

        return paths;
    }
}
