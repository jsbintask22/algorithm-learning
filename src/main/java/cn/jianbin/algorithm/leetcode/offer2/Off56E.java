package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.HashSet;

/**
 * @author aaron.zou
 * @date 2023/4/15 12:34 PM
 *
 * 剑指 Offer II 056. 二叉搜索树中两个节点之和
 * 给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。假设二叉搜索树中节点的值均唯一。
 *
 *
 *
 * 示例 1：
 *
 * 输入: root = [8,6,10,5,7,9,11], k = 12
 * 输出: true
 * 解释: 节点 5 和节点 7 之和等于 12
 * 示例 2：
 *
 * 输入: root = [8,6,10,5,7,9,11], k = 22
 * 输出: false
 * 解释: 不存在两个节点值之和为 22 的节点
 *
 *
 * 提示：
 *
 * 二叉树的节点个数的范围是  [1, 104].
 * -104 <= Node.val <= 104
 * root 为二叉搜索树
 * -105 <= k <= 105
 *
 *
 * 注意：本题与主站 653 题相同： https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
 */
@UtilityClass
public class Off56E {

    public static void main(String[] args) {

    }

    HashSet<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        boolean r1 = findTarget(root.left, k);
        if (r1) {
            return true;
        }

        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);


        return findTarget(root.right, k);
    }
}

