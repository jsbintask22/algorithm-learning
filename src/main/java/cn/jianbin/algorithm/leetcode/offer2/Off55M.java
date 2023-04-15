package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author aaron.zou
 * @date 2023/4/15 12:16 PM
 */
@UtilityClass
public class Off55M {

    public static void main(String[] args) {

    }

    public static class BSTIterator {
        private Deque<TreeNode> stack = new LinkedList<>();
        private TreeNode cur;

        public BSTIterator(TreeNode root) {
            cur =root;
        }

        public int next() {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            // cur 已经在整颗数最左边；
            // 弹出；
            TreeNode top = stack.pop();
            int ret = top.val;
            // 弹出之后，检查 top 的右边是否有节点；
            cur = top.right;

            return ret;
        }

        public boolean hasNext() {
            return cur != null || !stack.isEmpty();
        }
    }
}
