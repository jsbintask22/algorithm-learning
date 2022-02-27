package cn.jianbin.algorithm.binarytree;

import cn.jianbin.alogthrim.datastructure.BinTreeNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author aaron.zou
 * @date 2022/1/15 8:11 下午
 */
@UtilityClass
public class BinTreeTraverse {
    public static void main(String[] args) {
        BinTreeNode root = BinTreeNode.gen(5);
        pre(root);
        System.out.println();
        BinTreeNode.inTraversal(root);
        BinTreeNode.postTraversal(root);
        System.out.println("---------------------------------------------------");

        BinTreeNode r2 = BinTreeNode.gen(5);
        System.out.println("root equals r2 : " + bintreeEquals(r2, r2));
        System.out.println("---------------------------------------------------");

        BinTreeNode r3 = BinTreeNode.gen(3);
        System.out.println("r1 height = " + maxDepth(root) + " r2 = " + maxDepth(r2) + " r3 = " + maxDepth(r3));
        System.out.println("---------------------------------------------------");

        BinTreeNode r4 = BinTreeNode.gen(2);
        System.out.println("is mirror tree: " + isMirrorTree(root) + ", " + isMirrorTree(r2) + ", " + isMirrorTree(r3) + ", " + isMirrorTree(r4));
        System.out.println("---------------------------------------------------");



        List<List<Integer>> ret = levelCollNode(BinTreeNode.gen(4));
        System.out.println("tree level node count;");
        ret.forEach(System.out::println);
        System.out.println("---------------------------------------------------");

        // 是否平衡二叉树
        System.out.println("root is balanced: " + balanced(root));
        System.out.println("r2 is balanced: " + balanced(r2));
        System.out.println("r3 is balanced: " + balanced(r3));
        System.out.println("r4 is balanced: " + balanced(r4));
        BinTreeNode r5 = new BinTreeNode(1);
        r5.right = new BinTreeNode(2);
        r5.left = new BinTreeNode(3);
        r5.right.left = new BinTreeNode(4);
        r5.right.left.left = new BinTreeNode(5);
        System.out.println("r5 is balanced: " + balanced(r5));
        System.out.println("---------------------------------------------------");

        // 是否搜索数
        BinTreeNode r6 = new BinTreeNode(7);
        r6.left = new BinTreeNode(25);

        r6.right = new BinTreeNode(10);
        r6.right.left = new BinTreeNode(8);
        r6.right.left.left = new BinTreeNode(7);
        System.out.println("r5 is bts : " + getBts(r5) + " r6 is bts: " + getBts(r6));
        System.out.println("r7 is bts: " + getBts(BinTreeNode.gen(5)));
        System.out.println("r8 is bts: " + getBts(BinTreeNode.gen(4)));
        System.out.println("r9 is bts: " + getBts(BinTreeNode.gen(3)));
        System.out.println("r10 is bts: " + getBts(BinTreeNode.gen(2)));
        System.out.println("r11 is bts: " + getBts(BinTreeNode.gen(1)));
        System.out.println("---------------------------------------------------");

        // 路径和
        System.out.println("r6 sum 8 : " + hasSumPath(r6, 0, 8));
        System.out.println("r6 sum 8 : " + hasSumPath(r6, 0, 32));
        List<List<Integer>> pathRet = new ArrayList<>();
        hasSumPath(r6, Collections.emptyList(), 0, 32,
                pathRet);
        System.out.println("r6 sum 32 path: " + pathRet);
        System.out.println("---------------------------------------------------");




        root = BinTreeNode.genUnique(5);
        System.out.println("rebuild; ");
        pre(rebuildTree(BinTreeNode.preTraversal(root), BinTreeNode.inTraversal(root)));
        System.out.println();
        System.out.println("---------------------------------------------------");

    }

    /**
     *  递归序； 一个方法最少进一次； 递归完后还要回到原方法；
     */
    public void pre(BinTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        pre(root.left);
        pre(root.right);
    }

    /**
     *  求两个二叉树是否一样（左右节点是否相等）
     */
    public boolean bintreeEquals(BinTreeNode r1, BinTreeNode r2) {
        // 如果有一个阶段任一为空，则返回 false
        if (r1 != null && r2 == null) {
            return false;
        }

        if (r2 != null && r1 == null) {
            return false;
        }

        // 如果两个都为空，返回true
        if (r1 == null && r2 == null) {
            return true;
        }

        // 到这里，两个都不为空，递归左右节点
        return r1.val == r2.val && (bintreeEquals(r1.left, r2.left) && bintreeEquals(r1.right, r2.right));
    }

    /**
     *  求二叉树最大深度
     */
    public int maxDepth(BinTreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     *  求二叉树是否镜面树
     */
    public boolean isMirrorTree(BinTreeNode root) {
        if (root == null) {
            return true;
        }

        return isMirrorTree(root.right, root.left);
    }

    private boolean isMirrorTree(BinTreeNode left, BinTreeNode right) {
        if ((left == null) ^ (right == null)) {
            return false;
        }

        if (left == null && right == null) {
            return true;
        }

        return left.val == right.val && isMirrorTree(left.left, right.right) && isMirrorTree(left.right, right.left);
    }

    /**
     *  根据一颗二叉树的 先序和 中序，重构二叉树
     */
    public BinTreeNode rebuildTree(int[] pre, int[] in) {
        // 先找到头结点； pre 的第一个肯定是头结点
        if (pre == null || in == null || pre.length == 0 || pre.length != in.length) {
            return null;
        }

        Map<Integer, Integer> inIdxMap = new HashMap<>(in.length);
        for (int i = 0; i < in.length; i++) {
            inIdxMap.put(in[i], i);
        }

        // return rebuildTree(pre, 0, pre.length - 1, in, 0, pre.length - 1);
        return rebuildTree(pre, 0, pre.length - 1, in, 0, pre.length - 1, inIdxMap);
    }

    public BinTreeNode rebuildTree(int[] pre, int l1, int r1, int[] in, int l2, int r2) {
        if (l1 > r1) {
            return null;
        }

        if (r1 - l1 != r2 - l2) {
            return null;
        }

        // 先找到头结点； pre 的第一个肯定是头结点
        BinTreeNode root = new BinTreeNode(pre[l1]);

        // 找出头结点在中序中的位置
        int headIdx = l2;
        while (in[headIdx] != pre[l1]) {
            headIdx++;
        }

        // 找到头结点位置后，从这个节点开始，l2 - headIdx 的未知属于左子树， headIdx - r2 的未知都属于右子树
        root.left = rebuildTree(pre, l1 + 1, (l1 + (headIdx - l2)), in, l2, headIdx - 1);
        root.right = rebuildTree(pre, (l1 + headIdx - l2) + 1, r1, in, headIdx + 1, r2);

        return root;
    }

    public BinTreeNode rebuildTree(int[] pre, int l1, int r1, int[] in, int l2, int r2, Map<Integer, Integer> inIdxMap) {
        if (l1 > r1) {
            return null;
        }

        if (r1 - l1 != r2 - l2) {
            return null;
        }

        // 先找到头结点； pre 的第一个肯定是头结点
        BinTreeNode root = new BinTreeNode(pre[l1]);

        // 找出头结点在中序中的位置
        int headIdx = inIdxMap.get(pre[l1]);

        // 找到头结点位置后，从这个节点开始，l2 - headIdx 的未知属于左子树， headIdx - r2 的未知都属于右子树
        root.left = rebuildTree(pre, l1 + 1, (l1 + (headIdx - l2)), in, l2, headIdx - 1, inIdxMap);
        root.right = rebuildTree(pre, (l1 + headIdx - l2) + 1, r1, in, headIdx + 1, r2, inIdxMap);

        return root;
    }

    /**
     *  按层遍历并且收集每层的节点返回(倒序从最底层返回）
     */
    public List<List<Integer>> levelCollNode(BinTreeNode root) {
        List<List<Integer>> ret = new LinkedList<>();
        if (root == null) {
            return ret;
        }

        // 现将头结点放入
        Queue<BinTreeNode> queue = new LinkedList<>();
        queue.add(root);
        int pollCount = 1;


        while (!queue.isEmpty()) {
            List<Integer> curLevelList = new LinkedList<>();
            while (pollCount-- != 0) {
                BinTreeNode top = queue.poll();
                curLevelList.add(top.val);

                if (top.left != null) {
                    queue.add(top.left);
                }

                if (top.right != null) {
                    queue.add(top.right);
                }
            }

            ret.add(0, curLevelList);
            pollCount = queue.size();
        }


        return ret;
    }

    /**
     *  一颗二叉树是否平衡
     *  也即每个节点的左右子节点的高度差不超过 1
     *
     *  递归每个子节点；返回子节点是否平衡以及高度；才能得到当前节点是否平衡
     */
    public boolean isBalanced(BinTreeNode root) {
        if (root == null) {
            return true;
        }

        return balanced(root).balanced;
    }

    private Balanced balanced(BinTreeNode root) {
        if (root == null) {
            return new Balanced(0, true);
        }

        // 得到左节点的 平衡信息
        Balanced leftBalanced = balanced(root.left);

        // 得到右节点的 平衡信息
        Balanced rightBalanced = balanced(root.right);

        // 当前节点是否平衡取决于子节点是否平衡 以及 左右节点的高度差
        int height = Math.max(leftBalanced.height, rightBalanced.height) + 1;

        boolean balanced = leftBalanced.balanced && rightBalanced.balanced
                && Math.abs(leftBalanced.height - rightBalanced.height) <= 1;

        return new Balanced(height, balanced);
    }

    @AllArgsConstructor
    @Data
    private static class Balanced {
        private int height;
        private boolean balanced;
    }

    /**
     *  二叉树是否是搜索二叉树
     *
     *  每个节点的左节点（包括所有子节点）为空或者小于当前节点
     *  每个节点的右节点（包括所有子节点）为空或者大于当前节点
     *
     *  m1: 直接返回 后续遍历  左 右 中
     *  必须从小到大排列；
     *
     *  m2：递归判断每个子节点是否满足搜索二叉树
     */
    public boolean isSearched(BinTreeNode root) {
        if (root == null) {
            return true;
        }

        return getBts(root).bts;
    }

    public Bts getBts(BinTreeNode root) {
        if (root == null) {
            return new Bts(true, -1, -1);
        }

        // 得到左节点是否bts
        Bts leftBts = getBts(root.left);

        // 得到有节点是否 bts
        Bts rightBts = getBts(root.right);

        // 当前节点是bts条件为； 大于左数 最大值； 小于右数最小值
        int min = Math.min(leftBts.min, rightBts.min);

        int max = Math.max(leftBts.max, rightBts.max);

        boolean bts = root.val >= leftBts.max && (max == -1 || root.val <= rightBts.min);

        int minVal = Math.min(min, root.val);
        int maxVal = Math.max(max, root.val);
        return new Bts(bts && leftBts.bts && rightBts.bts,
                maxVal == -1 ? root.val : maxVal,
                minVal == -1 ? root.val : minVal);
    }

    @AllArgsConstructor
    @ToString
    private static class Bts {
        private boolean bts;
        private int max;
        private int min;
    }

    public boolean hasSumPath(BinTreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        return hasSumPath(root, 0, sum);
    }

    /**
     *  求一颗二叉树上是否 有一条满足 所有节点和 = sum 的路径
     */
    private boolean hasSumPath(BinTreeNode root, int preSum, int sum) {
        // 如果当前已经是叶子结点，计算已经累加的值 是否 满足了
        if (root.left == null && root.right == null) {
            return root.val + preSum == sum;
        }

        // 否则，还没到叶子结点； 要继续遍历
        boolean left = false;
        if (root.left != null) {
            left =  hasSumPath(root.left, root.val + preSum, sum);
        }

        boolean right = false;
        if (root.right != null) {
            right =  hasSumPath(root.right, root.val + preSum, sum);
        }

        return left || right;
    }

    /**
     *  将所有路径和是 sum 的路径保存起来放到 ret里面
     */
    private void hasSumPath(BinTreeNode cur, List<Integer> prePath,
                            int preSum,
                            int sum, List<List<Integer>> ret) {
        if (cur.left == null && cur.right == null) {
            if (cur.val + preSum == sum) {
                prePath.add(cur.val);
                ret.add(sumList(prePath));
                return;
            }
        }

        if (cur.left != null) {
            List<Integer> newList = sumList(prePath);
            newList.add(cur.val);
            hasSumPath(cur.left, newList, cur.val + preSum, sum, ret);
        }

        if (cur.right != null) {
            List<Integer> newList = sumList(prePath);
            newList.add(cur.val);
            hasSumPath(cur.right, newList, cur.val + preSum, sum, ret);
        }
    }

    private List<Integer> sumList(List<Integer> prePath) {
        return new LinkedList<>(prePath);
    }

}
