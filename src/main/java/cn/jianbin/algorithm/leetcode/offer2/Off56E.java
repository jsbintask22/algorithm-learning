package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.leetcode.offer2.data.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.HashSet;

/**
 * @author aaron.zou
 * @date 2023/4/15 12:34 PM
 */
@UtilityClass
public class Off56E {

    public static void main(String[] args) {

    }

    HashSet<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        boolean r1 = findTarget(root.left, k);
        if (r1) {
            return true;
        }

        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);


        return findTarget(root.right, k);
    }
}

