package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/4/13 7:14 PM
 *
 * 剑指 Offer II 051. 节点之和最大的路径
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给定一个二叉树的根节点 root ，返回其 最大路径和，即所有路径上节点值之和的最大值。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 *
 *
 *
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 *
 * 提示：
 *
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 *
 *
 * 注意：本题与主站 124 题相同： https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 */
@UtilityClass
public class Off51H {

    public static void main(String[] args) {

    }

    public int maxPathSum(TreeNode root) {
        solution(root);

        return maxSum;
    }

    int maxSum = 0;
    public int solution(TreeNode root) {
        // 解法； 后续遍历；  左、右、中  顺序；
        // 统计 当前节点 作为起点所能达到的最大值（这是要返回给上一层的）
        // ，然后 当前节点 + 左节点 + 右节点 算出当前节点 作为 中间节点
        // 所能形成的 最大路径和，和全局变量比较谁最大；

        // 2,-1

        if (root == null) {
            // 空节点，当前节点作为起点 到达其它节点的最大值 肯定为 0；
            return 0;
        }

        // 分别得到 当前节点 root 的左右节点 所能贡献的最大值
        int leftGain = solution(root.left);
        int rightGain = solution(root.right);

        // 统计一下，当前节点作为 中间节点 是不是最大的；
        int curVal = root.val;

        maxSum = Math.max(maxSum, leftGain + rightGain + curVal);

        // 返回 以当前节点为 起点，所能得到 的最大值（加上左 OR 加上右 OR 两个都不加 OR 自己都不加）
        int childMaxGain = Math.max(leftGain, rightGain);
        if (childMaxGain > 0) {
            curVal += childMaxGain;
        }

        return Math.max(0, curVal);
    }
}
