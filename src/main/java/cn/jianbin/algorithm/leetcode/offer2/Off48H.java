package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author aaron.zou
 * @date 2023/4/12 7:15 PM
 *
 *
 * 剑指 Offer II 048. 序列化与反序列化二叉树
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * 示例 2：
 *
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 *
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，也可以采用其他的方法解决这个问题。
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 *
 *
 * 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 */
@UtilityClass
public class Off48H {

    public static void main(String[] args) {

    }

    public static class Codec {
        // 序列化-反序列化；
        // 解法； 使用先序遍历的方式 中、左、右 变成一个字符串；
        //     1
        //  2    3
        //    4    5
        // 1 - 2 - null - 4 - null - null - 3 - null - 5 - null - null

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<String> ret = new LinkedList<>();
            serialize(root, ret);
            return String.join(",", ret);
        }

        private void serialize(TreeNode cur, List<String> ret) {
            if (cur == null) {
                // 如果当前节点已经为了，不需要往下面走了； 返回 'null'
                ret.add("null");
                return;
            }

            ret.add(String.valueOf(cur.val));
            serialize(cur.left, ret);
            serialize(cur.right, ret);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            List<String> ret = Arrays.stream(data.split(","))
                    .collect(Collectors.toList());

            return deserialize(ret);
        }

        private TreeNode deserialize(List<String> ret) {
            // 按照 先序遍历的方式； 中、左、右 一次次把结果反构出来；
            if (ret.size() == 0) {
                return null;
            }

            String head = ret.remove(0);
            if ("null".equals(head)) {
                // 空节点； 直接返回 null
                return null;
            }

            // 1 - 2 - null - 4 - null - null - 3 - null - 5 - null - null
            TreeNode root = new TreeNode(Integer.parseInt(head));
            root.left = deserialize(ret);
            root.right = deserialize(ret);

            return root;
        }
    }
}
