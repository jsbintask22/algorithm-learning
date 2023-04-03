package cn.jianbin.algorithm.binarytree;

import cn.jianbin.algorithm.utils.Utils;
import cn.jianbin.alogthrim.datastructure.BinTreeNode;
import cn.jianbin.alogthrim.datastructure.BinTreeNode2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.UtilityClass;

import java.util.*;

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


        print(1, 3, 0);

        System.out.println("---------------------------------------------------");
        System.out.println("求二叉树最大距离");
        System.out.println(maxDistance(BinTreeNode.gen(3)));



        System.out.println("---------------------------------------------------");
        System.out.println("求二叉树中的最大搜索子树节点个数");
        System.out.println(maxSubBstSize(BinTreeNode.genUnique(4)));


        System.out.println("---------------------------------------------------");
        System.out.println("求二叉树中的最大快乐值");
        System.out.println(getMaxVal(BinTreeNode.gen(4)));


        System.out.println("---------------------------------------------------");
        System.out.println("求二叉树是否满二叉树");
        System.out.println(getFullSize(Utils.genBinTree()));


        System.out.println("---------------------------------------------------");
        System.out.println("求二叉树中的最大搜索子树");
        System.out.println(getMaxSubBstNode(BinTreeNode.genUnique(5)));



        System.out.println("---------------------------------------------------");
        System.out.println("判断二叉树是否完全二叉树");
        System.out.println(isCompleteTree(BinTreeNode.genUnique(5)));
    }

    public void pre(BinTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        pre(root.left);
        pre(root.right);
    }

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

    public int maxDepth(BinTreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

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

    public BinTreeNode2 getSuccessorNode(BinTreeNode2 node) {
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return getMostLeftNode(node.right);
        }

        // 右节点为空
        BinTreeNode2 parent = node.parent;
        while (parent != null && parent.right == node) {
            node = parent;
            parent = parent.parent;
        }

        return parent;
    }


    private BinTreeNode2 getMostLeftNode(BinTreeNode2 node) {
        BinTreeNode2 left = node.left;
        while (left != null) {
            node = left;
            left = left.left;
        }

        return node;
    }


    public void print(int cur, int max, int flag) {
        if (cur > max) {
            return;
        }
        print(cur + 1, max, 1);
        System.out.println(flag == 1 ? "凸" : "凹");
        print(cur + 1, max, 0);
    }

    public Info maxDistance(BinTreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }

        Info leftInfo = maxDistance(root.left);
        Info rightInfo = maxDistance(root.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(Math.max(leftInfo.distance, rightInfo.distance),
                leftInfo.height + rightInfo.height + 1);

        return new Info(maxDistance, height);
    }


    @AllArgsConstructor
    @ToString
    static class Info {
        int distance;
        int height;
    }

    public Info2 maxSubBstSize(BinTreeNode root) {
        // 如果当前节点 = null；  最大搜索子节点个数 = 0，isBst = true，但是max 和 min val不好设置，干脆直接设置null
        // 用的时候做判断，否则要用某些特殊值去标识 不太好处理;
        if (root == null) {
            return null;
        }

        Info2 left = maxSubBstSize(root.left);
        Info2 right = maxSubBstSize(root.right);

        // 1. root 的最大值、最小值  来源于 两边的子树
        int minVal = root.val;
        int maxVal = root.val;

        if (left != null) {
            minVal = Math.min(left.minVal, minVal);
            maxVal = Math.max(left.maxVal, maxVal);
        }

        if (right != null) {
            minVal = Math.min(right.minVal, minVal);
            maxVal = Math.max(right.maxVal, maxVal);
        }


        // 2. root 的最大搜索子树，假设跟 X 无关；
        int subMaxSize = 0;
        if (left != null) {
            // 最大子节点来源于 左节点
            subMaxSize = left.maxSubBstSize;
        }

        if (right != null) {
            // 最大子节点来源于右节点
            subMaxSize = right.maxSubBstSize;
        }

        boolean isBst = false;
        // 3. root 是否搜索树
        // 要满足三个条件；
        // 左、右 子树都是 搜索树
        // 左树最大节点 < 当前值，右树最小节点 > 当前值
        if (
                (left == null || left.isBst)
                        &&
                        (right == null || right.isBst)
                        &&
                        (left == null || left.maxVal < root.val)
                        && (right == null || right.minVal > root.val)

        ) {
            isBst = true;
            subMaxSize = (left == null ? 0 : left.maxSubBstSize)
                    + (right == null ? 0 : right.maxSubBstSize)
                    + 1;
        }

        return new Info2(subMaxSize, isBst, maxVal, minVal);
    }

    @AllArgsConstructor
    @ToString
    static class Info2 {


        int maxSubBstSize;

        boolean isBst;

        int maxVal;

        int minVal;
    }

    public Info3 getMaxVal(BinTreeNode root) {
        // base case: 如果已经是叶子结点了，则来或者不来的最大快乐值能一下得到
        if (root.left == null && root.right == null) {
            return new Info3(root.val, 0);
        }

        // 先初始化 当前节点 来与不来的 最大快乐值
        int yes = root.val;
        int no = 0;

        if (root.left != null) {
            // 左节点不为空。 X 来的最大快乐值 + 做节点不来的最大快乐值
            Info3 leftMax = getMaxVal(root.left);
            yes += leftMax.no;

            // X 不来的最大快乐值；  求左节点来与不来的最大快乐值

            no += Math.max(leftMax.yes, leftMax.no);
        }

        // 右节点不为空，同上
        if (root.right != null) {
            Info3 rightMax = getMaxVal(root.right);
            // X 来 + 右节点不来最大快乐值
            yes += rightMax.no;

            // X 不来 + 右节点来与不来的最大快乐值
            no += Math.max(rightMax.yes, rightMax.no);
        }


        return new Info3(yes, no);
    }


    @AllArgsConstructor
    @ToString
    static class Info3 {
        int yes;
        int no;
    }

    public Info4 getFullSize(BinTreeNode root) {
        // base case； 空节点，则高度为 0 ，子节点个数为1；
        // base case2: 直接判断
        if (root == null) {
            return new Info4(0, 0);
        }

        Info4 leftFull = getFullSize(root.left);
        Info4 rightFull = getFullSize(root.right);

        // 得到当前节点高度，要把自己算上哦
        int height = Math.max(leftFull.height, rightFull.height) + 1;

        // 节点个数为 左节点个数 + 右节点个数 + 1；
        int size = leftFull.size + rightFull.size + 1;

        return new Info4(height, size);
    }

    @AllArgsConstructor
    @ToString
    static class Info4 {
        int height;
        int size;
    }


    public Info5 getMaxSubBstNode(BinTreeNode root) {
        // base case; root == null， 直接构造信息返回； 但是最大值、最小值不好定义；所以直接返回null
        // 后面用的时候要主要 判空即可
        if (root == null) {
            return null;
        }

        Info5 left = getMaxSubBstNode(root.left);
        Info5 right = getMaxSubBstNode(root.right);

        // 1. 得到最大值、最小值
        int maxVal = root.val;
        int minVal = root.val;
        if (left != null) {
            // 左节点返回信息不为空， 则比较 左节点最大值、最小值
            maxVal = Math.max(maxVal, left.maxVal);
            minVal = Math.min(minVal, left.minVal);
        }

        if (right != null) {
            // 右节点返回信息不为空，则比较 右节点最大值、最小值
            maxVal = Math.max(maxVal, right.maxVal);
            minVal = Math.min(minVal, right.minVal);
        }


        // 2. 初始化 最大搜索子树的 节点个数为； 假设和当前节点无关
        int maxSubBstSize = 0;
        BinTreeNode maxSubBstNode = null;
        if (left != null) {
            maxSubBstSize = left.maxSubBstSize;
            maxSubBstNode = left.maxSubBstNode;
        }

        if (right != null) {
            boolean isRight = right.maxSubBstSize > maxSubBstSize;

            if (isRight) {
                maxSubBstSize = right.maxSubBstSize;
                maxSubBstNode = right.maxSubBstNode;
            }
        }

        // 3. 加入和 X 有关； 则表示 X 就是当前的最大子节点；
        // 那么 X 的左右几点要满足 如下情况；
        // 3.1  左、右 都是二叉搜索树
        // 3.2 左最大值 < X ，右最小值 > X
        if (
                (left == null || left.maxVal < root.val)
                &&
                        (right == null || right.minVal > root.val)
                &&
                        (left == null || left.maxSubBstNode == root.left)
                &&
                        (right == null || right.maxSubBstNode == root.right)

        ) {

            maxSubBstNode = root;
            maxSubBstSize = (left == null ? 0 : left.maxSubBstSize)
                    + (right == null ? 0 : right.maxSubBstSize)
                    + 1;
        }


        return new Info5(maxSubBstNode, maxSubBstSize, maxVal, minVal);
    }

    @AllArgsConstructor
    @ToString
    static class Info5 {
        BinTreeNode maxSubBstNode;
        int maxSubBstSize;
        int maxVal;
        int minVal;
    }

    public boolean isCompleteTree(BinTreeNode root) {
        if (root == null) {
            // == null，为false
            return false;
        }

        Queue<BinTreeNode> queue = new LinkedList<>();
        // 把根节点压入
        queue.add(root);
        boolean firstNotComplete = false;
        while (!queue.isEmpty()) {
            BinTreeNode top = queue.poll();

            BinTreeNode left = top.left;
            BinTreeNode right = top.right;
            if (left != null && right == null) {
                return false;
            }

            if (!firstNotComplete && (right == null || left == null)) {
                firstNotComplete = true;
            }

            if (firstNotComplete && (left != null || right != null)) {
                return false;
            }

            if (left != null) {
                queue.add(left);
            }

            if (right != null) {
                queue.add(right);
            }
        }

        return true;
    }

    public BinTreeNode mostRecentAncestors(BinTreeNode root, BinTreeNode node1, BinTreeNode node2) {
        if (root == null || node1 == null || node2 == null) {
            return null;
        }

        Queue<BinTreeNode> queue = new LinkedList<>();
        queue.add(root);
        Map<BinTreeNode, BinTreeNode> map = new HashMap<>();
        map.put(root, null);
        while (!queue.isEmpty()) {
            BinTreeNode head = queue.poll();
            if (head.left != null) {
                queue.add(head.left);
                map.put(head.left, head);
            }

            if (head.right != null) {
                queue.add(head.right);
                map.put(head.right, head);
            }
        }

        Set<BinTreeNode> ancestors = new HashSet<>();
        BinTreeNode cur = node1;
        ancestors.add(cur);
        while (cur != null) {
            BinTreeNode ancestor = map.get(cur);
            ancestors.add(ancestor);
            cur = ancestor;
        }

        cur = node2;
        while (!ancestors.contains(cur)) {
            cur = map.get(cur);
        }

        return cur;
    }



    public boolean hasSumPath(BinTreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        return hasSumPath(root, 0, sum);
    }

    private boolean hasSumPath(BinTreeNode root, int preSum, int sum) {
        // 如果当前已经是叶子结点，计算已经累加的值 是否 满足了
        if (root.left == null && root.right == null) {
            return root.val + preSum == sum;
        }

        // 否则，还没到叶子结点； 要继续遍历
        boolean left = false;
        if (root.left != null) {
            left = hasSumPath(root.left, root.val + preSum, sum);
        }

        boolean right = false;
        if (root.right != null) {
            right = hasSumPath(root.right, root.val + preSum, sum);
        }

        return left || right;
    }

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
