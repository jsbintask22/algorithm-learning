package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.*;

/**
 * @author aaron.zou
 * @date 2023/4/11 9:03 PM
 *
 * 剑指 Offer II 046. 二叉树的右侧视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 *
 *
 * 示例 1:
 *
 *
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 * 示例 2:
 *
 * 输入: [1,null,3]
 * 输出: [1,3]
 * 示例 3:
 *
 * 输入: []
 * 输出: []
 *
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100
 *
 *
 * 注意：本题与主站 199 题相同：https://leetcode-cn.com/problems/binary-tree-right-side-view/
 */
@UtilityClass
public class Off46M {

    public static void main(String[] args) {

    }

    public List<Integer> solution(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        // 广度优先； 知道不为空，则遍历所有节点
        // 当时最后一个节点的时候，加入；

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        List<Integer> ret = new ArrayList<>();

        while (!deque.isEmpty()) {
            int size = deque.size();

            for (int i = 0; i < size; i++) {
                TreeNode cur = deque.poll();
                int val = cur.val;
                if (i == size - 1) {
                    // 最后一个；
                    ret.add(val);
                }

                if (cur.left != null) {
                    deque.offer(cur.left);
                }

                if (cur.right != null) {
                    deque.offer(cur.right);
                }
            }
        }

        return ret;
    }
}
