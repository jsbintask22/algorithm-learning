package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author aaron.zou
 * @date 2023/4/11 8:40 PM
 *
 *
剑指 Offer II 045. 二叉树最底层最左边的值
给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。

假设二叉树中至少有一个节点。



示例 1:



输入: root = [2,1,3]
输出: 1
示例 2:



输入: [1,2,3,4,null,5,6,null,null,7]
输出: 7


提示:

二叉树的节点个数的范围是 [1,104]
-231 <= Node.val <= 231 - 1


注意：本题与主站 513 题相同： https://leetcode-cn.com/problems/find-bottom-left-tree-value/
 */
@UtilityClass
public class Off45M {

    public static void main(String[] args) {

    }

    public int solution2(TreeNode root) {
        // 解法2； 深度优先；
        return 0;
    }

        public int solution(TreeNode root) {
        // 找最底层最左边的值；
        // 广度优先； 遍历每一层的节点； ** 按照先加右节点，再加左节点操作 **，这样便利的时候，最后一个节点就是 该层最左边的节点；

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int ret = 0;

        while (!deque.isEmpty()) {
            TreeNode top = deque.poll();
            if (top.right != null) {
                deque.offer(top.right);
            }
            if (top.left != null) {
                deque.offer(top.left);
            }

            // 最后一个节点 肯定是最左边的的
            ret = top.val;
        }

        return ret;
    }
}
