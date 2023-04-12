package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.StringJoiner;

/**
 * @author aaron.zou
 * @date 2023/4/12 7:43 PM
 *
 * 剑指 Offer II 049. 从根节点到叶节点的路径数字之和
 * 给定一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 *
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * 示例 2：
 *
 *
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 *
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 9
 * 树的深度不超过 10
 *
 *
 * 注意：本题与主站 129 题相同： https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
 */
@UtilityClass
public class Off49M {

    public static void main(String[] args) {

    }

    int sum = 0;

    public int solution(TreeNode root) {
        // 解法； 先序遍历到每个节点，如果遇到 叶子节点了，则直接结算当前结果，然后把 当前数字干掉再返回上一层；
        if (root == null) {
            return 0;
        }

        dfs(root, new StringBuilder());

        return sum;
    }

    public void dfs(TreeNode root, StringBuilder cur) {
        // 遇到叶子节点了，直接干掉；
        if (root.left == null && root.right == null) {
            // 结算一次；
            // 干掉 当前节点的值；(因为还没加进来，所以也不用干掉）
            sum += Integer.parseInt(cur.toString() + root.val);
            return;
        }

        // 把当前节点加进来；
        String str = String.valueOf(root.val);
        cur.append(str);

        //     1
        //  2     3
        //5  4
        // 1

        // 1251 + 124 + 13

        // 遍历左节点；
        if (root.left != null) {
            dfs(root.left, cur);
        }

        if (root.right != null) {
            dfs(root.right, cur);
        }

        // 从里面出来后以后，要把当前加的节点删掉； 30    130
        cur.delete(cur.length() - str.length(), cur.length());
    }
}
