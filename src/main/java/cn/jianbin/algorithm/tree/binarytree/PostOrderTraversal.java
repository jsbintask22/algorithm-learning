package cn.jianbin.algorithm.tree.binarytree;

import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;

import java.util.Arrays;

public class PostOrderTraversal {

    /**
     *   一直一个  二叉树  后序遍历的 数组  求原二叉树顺序
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {
        /**
         *
         *          5
         *       4      7
         *      3     6    8
         *     2
         *
         *
         *          4
         *             5
         *               6
         *                 7
         *                   8
         *
         *
         *     后序遍历数组：   2 3 4 6 8 7 5
         *                    8 7 6 5 4
         *
         *     先序遍历：      5 4 3 2 7 6 8
         *
         *     中序遍历：      2 3 4 5 6 7 8
         */


        System.out.println(getBinaryTreeByPostOrder(new int[]{2, 3, 4, 6, 8, 7, 5}));

        System.out.println(getBinaryTreeByPostOrder(new int[]{8, 7, 6, 5, 4}));
    }

    public static Node getBinaryTreeByPostOrder(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int length = arr.length;
        if (length == 1) {
            return new Node(arr[0]);
        }

        // 找出根节点
        int lastValue = arr[length - 1];
        Node root = new Node(lastValue);

        int l = 0;
        // 找出构造左节点所需要的 数据 索引位置   时间复杂度：  O(N2)
        // 优化：  使用二分查找                  O(N * log2N)
        int r = length - 1;
        for (int i = 0; i < length - 1; i++) {
            int v = arr[i];
            if (v > root.value) {
                r = i;
                break;
            }
        }

        // 构造左节点
        root.left = getBinaryTreeByPostOrder(Arrays.copyOfRange(arr, l, r));
        // 构造右节点
        root.right = getBinaryTreeByPostOrder(Arrays.copyOfRange(arr, r, length - 1));

        return root;
    }

    public static Node genBinaryTree(int min, int max, int level) {
        if (min >= max || level == 0) {
            return null;
        }

        int mid = RandomUtils.nextInt(min, max + 1);
        Node root = new Node(mid);
        root.level = level;
        root.left = genBinaryTree(0, mid, level - 1);
        root.left = genBinaryTree(mid + 1, max, level - 1);

        return root;
    }

    @Data
    static class Node {
        int value;
        Node left;
        Node right;
        int level;

        public Node(int value) {
            Node.this.value = value;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < level; i++) {
                sb.append(" ");
            }
            sb.append(value);
            boolean flag = false;
            if (left != null) {
                flag = true;
                sb.append("\n").append(left);
            }
            if (right != null) {
                if (!flag) {
                    sb.append("\n           ");
                } else {
                    sb.append("              ");
                }
                sb.append(right);
            }
            return sb.toString();
        }
    }
}
