package cn.jianbin.alogthrim.datastructure;

import cn.hutool.core.util.RandomUtil;
import cn.jianbin.algorithm.utils.Utils;
import lombok.Data;

import java.util.*;

/**
 * @author aaron.zou
 * @date 2021/12/12 3:25 下午
 */
@Data
public class BinTreeNode2 {
    public int val;
    public BinTreeNode2 left;
    public BinTreeNode2 right;
    public BinTreeNode2 parent;

    public BinTreeNode2(int val) {
        this.val = val;
    }

    /**
     * 先序遍历。   中 - 左（中-左-右） - 右（中-左-右）
     * 3
     * 10      20
     * 5     7
     */
    public static int[] preTraversal(BinTreeNode2 root) {
        ArrayList<Integer> ret = new ArrayList<>();
        preTraversal(root, ret);
        ret.forEach(r -> System.out.print(r + " "));
        System.out.println();

        return ret.stream().mapToInt(d -> d).toArray();
    }

    /**
     * 先序遍历的 精髓在于； 中 - 左 - 右
     * <p>
     * 先将头结点压入（因为先序头结点肯定要先打印）；
     * 接着依次 压入 根节点的 右 左 结点（如果有）
     * <p>
     * 因为压入后出栈的时候是要先打印左边的。  也就是说右边的结点总是比左边的结点先压入
     */
    public static void preWithStack(BinTreeNode2 root) {
        if (root == null) {
            return;
        }

        Stack<BinTreeNode2> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinTreeNode2 top = stack.pop();
            System.out.print(top.val + " ");
            if (top.right != null) {
                stack.push(top.right);
            }

            if (top.left != null) {
                stack.push(top.left);
            }
        }

        System.out.println();
    }

    public static void preTraversal(BinTreeNode2 root, ArrayList<Integer> arr) {
        if (root == null) {
            return;
        }

        arr.add(root.val);
        preTraversal(root.left, arr);
        preTraversal(root.right, arr);
    }

    public static int[] inTraversal(BinTreeNode2 root) {
        ArrayList<Integer> ret = new ArrayList<>();
        inTraversal(root, ret);
        ret.forEach(r -> System.out.print(r + " "));
        System.out.println();

        return ret.stream().mapToInt(d -> d).toArray();
    }

    public static void inTraversal(BinTreeNode2 root, ArrayList<Integer> arr) {
        if (root == null) {
            return;
        }

        inTraversal(root.left, arr);
        arr.add(root.val);
        inTraversal(root.right, arr);
    }

    public static void inWithStack(BinTreeNode2 root) {
        if (root == null) {
            return;
        }

        // 中序遍历 使用 stack 的精髓在于 脑海中想象图形；
        // 总是处理 二叉树的左斜边 入栈。   并且在出栈的时候检查右节点同样可以当做左斜边处理
        Stack<BinTreeNode2> leftStack = new Stack<>();
        while (!leftStack.isEmpty() || root != null) {
            if (root != null) {
                // 最左不等于空，入栈
                leftStack.push(root);
                root = root.left;
            } else {

                // 最左等于空了，出栈并且打印检查 右节点是否为空
                BinTreeNode2 top = leftStack.pop();
                System.out.print(top.val + " ");
                root = top.right;
            }
        }

        System.out.println();
    }

    public static int[] postTraversal(BinTreeNode2 root) {
        ArrayList<Integer> ret = new ArrayList<>();
        postTraversal(root, ret);
        ret.forEach(r -> System.out.print(r + " "));
        System.out.println();

        return ret.stream().mapToInt(d -> d).toArray();
    }

    public static void postTraversal(BinTreeNode2 root, ArrayList<Integer> arr) {
        if (root == null) {
            return;
        }

        postTraversal(root.left, arr);
        postTraversal(root.right, arr);
        arr.add(root.val);
    }

    /**
     * 后序遍历 stack 版本； 关键在于想象图形；
     * <p>
     * 先序遍历；  头 - 左 - 右   使用一个栈 将头压入栈顶； 然后再将右节点压入
     * <p>
     * 后序遍历   左 - 右 - 头
     *
     * @param root
     */
    public static void postWithStack(BinTreeNode2 root) {
        if (root == null) {
            return;
        }

        Stack<BinTreeNode2> stack = new Stack<>();
        stack.push(root);
        Stack<BinTreeNode2> ret = new Stack<>();
        while (!stack.isEmpty()) {
            // 先压入 左节点
            BinTreeNode2 top = stack.pop();
            ret.push(top);
            if (top.left != null) {
                stack.push(top.left);
            }

            // 在压右节点
            if (top.right != null) {
                stack.push(top.right);
            }
        }

        // 倒序出栈
        while (!ret.isEmpty()) {
            System.out.print(ret.pop().val + "  ");
        }
    }



    public static BinTreeNode2 gen(int len) {
        System.out.println();
        BinTreeNode2 root = new BinTreeNode2(RandomUtil.randomInt(0, 10));
        Deque<BinTreeNode2> parent = new LinkedList<>();
        parent.push(root);


        for (int i = 0; i < (len - 1); i++) {
            Deque<BinTreeNode2> children = new LinkedList<>();

            while (!parent.isEmpty()) {
                BinTreeNode2 top = parent.pop();
                for (int j = 0; j < ((len - i) * (len - i)) / 2; j++) {
                    System.out.print("  ");
                }
                System.out.print(top.val);
                top.left = new BinTreeNode2(RandomUtil.randomInt(0, 10));
                top.right = new BinTreeNode2(RandomUtil.randomInt(0, 10));

                children.offer(top.left);
                children.offer(top.right);
            }
            parent = children;
            System.out.println();
        }

        System.out.print("  ");
        while (!parent.isEmpty()) {
            System.out.print(parent.pop().val + "  ");
        }
        System.out.println();
        System.out.println("----------------------------------------------------");

        return root;
    }

    public static BinTreeNode2 genUnique(int len) {
        System.out.println();
        BinTreeNode2 root = new BinTreeNode2(RandomUtil.randomInt(0, 100));
        Set<Integer> set = new HashSet<>();
        Deque<BinTreeNode2> parent = new LinkedList<>();
        parent.push(root);
        set.add(root.val);


        for (int i = 0; i < (len - 1); i++) {
            Deque<BinTreeNode2> children = new LinkedList<>();

            while (!parent.isEmpty()) {
                BinTreeNode2 top = parent.pop();
                for (int j = 0; j < ((len - i) * (len - i)) / 2; j++) {
                    System.out.print("  ");
                }
                System.out.print(top.val);
                do {
                    top.left = new BinTreeNode2(RandomUtil.randomInt(0, 100));
                } while (set.contains(top.left.val));
                set.add(top.left.val);

                do {
                    top.right = new BinTreeNode2(RandomUtil.randomInt(0, 100));
                } while (set.contains(top.right.val));
                set.add(top.right.val);

                children.offer(top.left);
                children.offer(top.right);
            }
            parent = children;
            System.out.println();
        }

        System.out.print("  ");
        while (!parent.isEmpty()) {
            System.out.print(parent.pop().val + "  ");
        }
        System.out.println();
        System.out.println("----------------------------------------------------");

        return root;
    }

}
