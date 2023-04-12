package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.*;

/**
 * @author aaron.zou
 * @date 2023/4/11 7:52 PM
 *
 * 剑指 Offer II 044. 二叉树每层的最大值
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 *
 *
 *
 * 示例1：
 *
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 解释:
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 * 示例2：
 *
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 * 解释:
 *           1
 *          / \
 *         2   3
 * 示例3：
 *
 * 输入: root = [1]
 * 输出: [1]
 * 示例4：
 *
 * 输入: root = [1,null,2]
 * 输出: [1,2]
 * 解释:
 *            1
 *             \
 *              2
 * 示例5：
 *
 * 输入: root = []
 * 输出: []
 *
 *
 * 提示：
 *
 * 二叉树的节点个数的范围是 [0,104]
 * -231 <= Node.val <= 231 - 1
 *
 *
 * 注意：本题与主站 515 题相同： https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
 */
@UtilityClass
public class Off44M {

    public static void main(String[] args) {

    }

    public List<Integer> dfs(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        // 根据深度优先，先序遍历；
        List<Integer> ret = new ArrayList<>();
        dfs(root, 0, ret);

        return ret;
    }

    private void dfs(TreeNode cur, int curHeight, List<Integer> ret) {
        if (ret.size() == curHeight) {
            // 当前高度 = 结果中的个数
            ret.add(cur.val);
        } else {
            ret.set(curHeight, Math.max(ret.get(curHeight), cur.val));
        }

        if (cur.left != null) {
            dfs(cur.left, curHeight + 1, ret);
        }

        if (cur.right != null) {
            dfs(cur.right, curHeight + 1, ret);
        }
    }

    public List<Integer> solution(TreeNode root) {
        // 求每层的最大值；
        // 解法：广度遍历； 每次遍历都把队列中的值遍历空为止，然后找出最大值；
        if (root == null) {
            return Collections.emptyList();
        }

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        List<Integer> ret = new ArrayList<>();


        while (!deque.isEmpty()) {
            int max = 0;
            int len = deque.size();

            // 一直要弹出 len 个节点，这一层才算结束了；
            while (len-- > 0) {
                TreeNode top = deque.poll();
                max = Math.max(max, top.val);

                // 这里弹出以后还是要把 子节点加入，因为这里巧妙地利用了 len 变量判断本次结束了；
                if (top.left != null) {
                    deque.offer(top.left);
                }

                if (top.right != null) {
                    deque.offer(top.right);
                }
            }

            ret.add(max);
        }

        return ret;
    }
}
