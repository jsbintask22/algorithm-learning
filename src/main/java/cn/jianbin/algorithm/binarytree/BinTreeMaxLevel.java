package cn.jianbin.algorithm.binarytree;

import cn.jianbin.algorithm.utils.Utils;
import cn.jianbin.alogthrim.datastructure.BinTreeNode;
import lombok.experimental.UtilityClass;

import java.util.*;

/**
 * @author aaron.zou
 * @date 2021/12/18 3:08 下午
 * 二叉树的最大层级
 */
@UtilityClass
public class BinTreeMaxLevel {
    public static void main(String[] args) {
        BinTreeNode root = Utils.genBinTree();
        System.out.println(maxNodeLevel(root));

        System.out.println(maxNodes2(root));
    }

    public int maxNodeLevel(BinTreeNode root) {
        if (root == null) {
            return 0;
        }

        // 求最大层级，可在层级遍历的基础上做文章（层级遍历是依次按层加入队列的）
        // 所以；  既然是按层级顺序加入队列的； 如果能知道每个节点所在的层级； 那就能知道下一层是否开启
        // 从而， 统计出上一层级的最大值； 以及知道当前层级

        Queue<BinTreeNode> queue = new ArrayDeque<>();
        // 将头结点加入； 并且初始化当前层级； 以及当前层级的最大值（注意，最大值是要当前层级遍历完了才统计的）
        queue.add(root);
        Map<BinTreeNode, Integer> levelMap = new HashMap<>();
        int curLevel = 1;
        levelMap.put(root, 1);
        int curLevelNodes = 0;
        int maxNodes = 0;

        while (!queue.isEmpty()) {
            BinTreeNode head = queue.poll();
            // 获取当前节点所在层级
            int curNodeLevel = levelMap.get(head);

            if (head.left != null) {
                queue.add(head.left);
                levelMap.put(head.left, curNodeLevel + 1);
            }

            if (head.right != null) {
                queue.add(head.right);
                levelMap.put(head.right, curNodeLevel + 1);
            }

            // 判断当前取出来的节点层 和 一开始保存的节点层是否一样；
            // 如果一样（初始层在第一层）； 说明还在这一层没有离开，当前层节点数 + 1
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                // 否则，说明下一层遍历已经开始了；  那就把上一层的个数和 max 机型比较并且清空
                // 最后层数 + 1
                maxNodes = Math.max(maxNodes, curLevelNodes);
                curLevelNodes = 1;
                curLevel++;
            }
        }

        // 最后出来的时候； 最后一层要进行结算； 想象图形； 遍历完了一层的最后一个节点后； 没有下一层了
        // 那当前层就没有和max进行比较（没有走到 else  分支）
        maxNodes = Math.max(maxNodes, curLevelNodes);

        // 扩展：如果是要求最多节点的层数；  有两个办法； 1. 直接遍历所有levelMap  再用一个map统计每层个数
        // 2. 再多加两个变量； 最大节点 层数；  最后遍历的层级

        return maxNodes;
    }

    public int maxNodes2(BinTreeNode root) {
        // 方法二； 不使用 map 去记录每个几点所在层数
        // 直接新增两个 变量； 一个记录当前层最后节点， 一个记录下一层 最后节点
        if (root == null) {
            return 0;
        }

        Queue<BinTreeNode> queue = new LinkedList<>();
        queue.add(root);
        BinTreeNode curEnd = root;
        BinTreeNode nextEnd = null;
        int maxNodes = 0;
        int curLevelNodes = 0;

        while (!queue.isEmpty()) {
            BinTreeNode curNode = queue.poll();
            // 弹出一个，当前层节点数 + 1
            curLevelNodes++;

            if (curNode.left != null) {
                queue.add(curNode.left);
                nextEnd = curNode.left;
            }

            if (curNode.right != null) {
                queue.add(curNode.right);
                nextEnd = curNode.right;
            }

            if (curEnd == curNode) {
                // 如果当前弹出节点 = 当前层最后一个节点； 要开始统计并且重置 当前层最后节点
                maxNodes = Math.max(maxNodes, curLevelNodes);
                curEnd = nextEnd;
                curLevelNodes = 0;
            }

        }

        return maxNodes;
    }
}
