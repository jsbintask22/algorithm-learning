package cn.jianbin.algorithm.leetcode.top100;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import cn.jianbin.algorithm.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jianbin.
 * @date 2023/7/29 15:31
 */
public class T102M {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        System.out.println(levelOrder(root));
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {


        LinkedList<TreeNode> queue = new LinkedList();

        queue.push(root);

        List<List<Integer>> ret = new ArrayList();

        while (!queue.isEmpty()) {
            // 队列，先进先出；

            // 把队列里面的全素全部取走；
            int size = queue.size();

            List<Integer> cur = new ArrayList();

            while (size-- > 0) {
                TreeNode head = queue.pop();

                if (head.left != null) {
                    queue.add(head.left);
                }

                if (head.right != null) {
                    queue.addLast(head.right);
                }

                cur.add(head.val);
            }

            ret.add(cur);
        }

        return ret;
    }

}
