package cn.jianbin.algorithm.binarytree;

import cn.jianbin.algorithm.utils.Utils;
import cn.jianbin.alogthrim.datastructure.BinTreeNode;
import lombok.experimental.UtilityClass;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@UtilityClass
public class BinTreeLevelPrint {
    public static void main(String[] args) {
        BinTreeNode root = Utils.genBinTree();
        level1(root);
    }


    public void level1(BinTreeNode root) {
        if (root == null) {
            return;
        }

        Queue<BinTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinTreeNode head = queue.poll();
            // 出队后打印
            System.out.print(head.val + " ");

            // 左、右 分别入队
            if (head.left != null) {
                queue.add(head.left);
            }

            if (head.right != null) {
                queue.add(head.right);
            }
        }
    }
}
