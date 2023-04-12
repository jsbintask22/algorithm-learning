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
