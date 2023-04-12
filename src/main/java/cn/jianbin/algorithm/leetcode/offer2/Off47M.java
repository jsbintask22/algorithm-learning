package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/4/12 6:54 PM
 *
 * 剑指 Offer II 047. 二叉树剪枝
 * 给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。
 *
 * 节点 node 的子树为 node 本身，以及所有 node 的后代。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1,null,0,0,1]
 * 输出: [1,null,0,null,1]
 * 解释:
 * 只有红色节点满足条件“所有不包含 1 的子树”。
 * 右图为返回的答案。
 *
 *
 * 示例 2:
 *
 * 输入: [1,0,1,0,0,0,1]
 * 输出: [1,null,1,null,1]
 * 解释:
 *
 *
 * 示例 3:
 *
 * 输入: [1,1,0,1,1,0,1,0]
 * 输出: [1,1,0,1,1,null,1]
 * 解释:
 *
 *
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [1,200]
 * 二叉树节点的值只会是 0 或 1
 *
 *
 * 注意：本题与主站 814 题相同：https://leetcode-cn.com/problems/binary-tree-pruning/
 */
@UtilityClass
public class Off47M {


    public static void main(String[] args) {

    }

    public TreeNode solution(TreeNode root) {
        // 解法；
        // 删除所有 全部为 0 的子节点；可以理解为；
        // 当前 节点 为0 并且左右节点也为0（或者空）
        // 用深度遍历，按照 左、右、中的原则去  从下到上依次 吧 满足条件的节点干掉；

        if (root == null) {
            // 退出条件； 当前节点为空了，直接返回空即可；
            return null;
        }

        // 当前节点不为空。 往下面遍历；
        // 先左节点，再右节点；
        // 遍历完了之后返回的结果 就是 当前节点的新的 左右节点；
        root.left = solution(root.left);
        root.right = solution(root.right);

        // 在 左右节点 已经返回（重构）的情况下，检查下当前节点是否可以被干掉；
        if (root.left == null && root.right == null && root.val == 0) {
            // 干掉当前节点；
            // 实际是 返回null
            root = null;
        }

        return root;
    }
}
