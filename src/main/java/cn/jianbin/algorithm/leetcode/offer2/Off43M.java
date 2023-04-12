package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author aaron.zou
 * @date 2023/4/11 6:46 PM
 *
 * 剑指 Offer II 043. 往完全二叉树添加节点
 * 完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 n 层有 2n-1 个节点）的，并且所有的节点都尽可能地集中在左侧。
 *
 * 设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
 *
 * CBTInserter(TreeNode root) 使用根节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个新节点，节点类型为 TreeNode，值为 v 。使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
 * CBTInserter.get_root() 将返回树的根节点。
 *
 *
 * 示例 1：
 *
 * 输入：inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
 * 输出：[null,1,[1,2]]
 * 示例 2：
 *
 * 输入：inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
 * 输出：[null,3,4,[1,2,3,4,5,6,7,8]]
 *
 *
 * 提示：
 *
 * 最初给定的树是完全二叉树，且包含 1 到 1000 个节点。
 * 每个测试用例最多调用 CBTInserter.insert  操作 10000 次。
 * 给定节点或插入节点的每个值都在 0 到 5000 之间。
 *
 *
 * 注意：本题与主站 919 题相同： https://leetcode-cn.com/problems/complete-binary-tree-inserter/
 *
 *
 */
@UtilityClass
public class Off43M {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        CBTInserter cbtInserter = new CBTInserter(root);
        System.out.println(cbtInserter.insert(2));
        System.out.println(cbtInserter.insert(3));
        System.out.println(cbtInserter.insert(4));
        System.out.println(cbtInserter.insert(5));
        System.out.println(cbtInserter.insert(6));

    }

    public static class CBTInserter {
        private Deque<TreeNode> condidates = new LinkedList<>();
        private TreeNode root;

        /**
         * 思路； 在已知是完全二叉树的情况下， 那么加入一个 节点，保持完全二叉的情况下，
         * 则这个节点 肯定是作为  某个节点的左节点 或者 右节点为空 的子节点
         *
         * 换句话说，就是 倒数第一层或者倒数第二层（只有这种情况）
         *
         * 如果把这些可能的节点先提前找出来，然后按照 层级优先，从左到右 放到一个队列里面，这样，加入新节点的时候
         * 弹出 队列的 头结点即可，然后加入以后判断要不要干掉这个节点；
         */
        public CBTInserter(TreeNode root) {
            // 先使用 广度优先 遍历，把 倒数第二、第一 层可能的节点找出来;
            this.root = root;
            Deque<TreeNode> deque = new LinkedList<>();
            deque.offer(root);

            while (!deque.isEmpty()) {
                // 不为空，找子节点；
                TreeNode cur = deque.poll();
                if (cur.left != null) {
                    // 左节点不为空，加入队列；
                    deque.offer(cur.left);
                }

                if (cur.right != null) {
                    // 右节点不为空，加到队列；
                    deque.offer(cur.right);
                }

                // 判断当前节点是否候选节点；
                if (cur.left == null || cur.right == null) {
                    condidates.offer(cur);
                }
            }
        }

        public int insert(int v) {
            TreeNode newChild = new TreeNode(v);

            // 从候选节点中弹出首节点；
            TreeNode top = condidates.peek();
            if (top.left == null) {
                top.left = newChild;
            } else {
                top.right = newChild;
                // 加到右节点，那当前节点就不是候选节点了（满了）
                condidates.poll();
            }

            // 加入了以后，那当前节点肯定是候选节点
            condidates.offer(newChild);

            return top.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }

}
