package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author aaron.zou
 * @date 2023/4/13 8:02 PM
 *
 * 剑指 Offer II 053. 二叉搜索树中的中序后继
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 *
 * 节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [2,1,3], p = 1
 * 输出：2
 * 解释：这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 * 示例 2：
 *
 *
 *
 * 输入：root = [5,3,6,2,4,null,null,1], p = 6
 * 输出：null
 * 解释：因为给出的节点没有中序后继，所以答案就返回 null 了。
 *
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 104] 内。
 * -105 <= Node.val <= 105
 * 树中各节点的值均保证唯一。
 *
 *
 * 注意：本题与主站 285 题相同： https://leetcode-cn.com/problems/inorder-successor-in-bst/
 */
@UtilityClass
public class Off53M {

    public static void main(String[] args) {

    }

    public TreeNode solution(TreeNode root, TreeNode p) {
        // 解法；按照中序遍历  左、中、右 的顺序加到链表中，然后 从链表中查找；
        // 解法2； 在遍历过程中 直接用 一个 map<cur, next> 记录下所有节点的下一个节点，可以省去 后面 o(n） 时间的查找

        // 解法3： 利用搜索二叉树的特点； 左节点一定比当前节点小，右节点一定比当前节点 大；
        // 从 root 开始，如果发现 root > p， 至少说明 root 已经是 "某个解了"， 接下来
        // 进一步 缩小 root的值（往左边走），再次判断，如果发现 root <= p 了，说明上一个解
        // 就是最小解了；

        TreeNode ret = null;

        // 先检查 p 的右边是否有节点；
        if (p.right != null) {
            // 有，说明 next 一定从p的右边出来；
            ret = p.right;

            TreeNode cur = ret;
            while (cur != null) {
                // 进一步 缩小结果； 因为左边肯定比 现在小（但是又肯定比 p 大）
                ret = cur;
                cur = cur.left;
            }

            return ret;
        }

        // p的右边没值，则答案一定在 p的父节点；
        while (root != null) {
            if (root.val > p.val) {
                // root > p， root 是潜在结果，进一步缩小 root 的值，往左边移动；
                ret = root;
                root = root.left;
            } else {
                // root <= p，说明上一步找到的 结果 是最小值了，返回；
                root = root.right;
            }
        }

        return ret;
    }

}
