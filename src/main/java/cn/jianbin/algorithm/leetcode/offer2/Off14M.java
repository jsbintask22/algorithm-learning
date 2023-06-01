package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jianbin.
 * @date 2023/4/4 20:52
 * <p>
 * 剑指 Offer II 014. 字符串中的变位词
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * <p>
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 * <p>
 * <p>
 * 注意：本题与主站 567 题相同： https://leetcode-cn.com/problems/permutation-in-string/
 */
@UtilityClass
public class Off14M {


    public static void main(String[] args) {
        System.out.println(solution("abc", "xybca"));
    }

    public boolean solution(String s1, String s2) {
        // 解法；滑动窗口；
        // 定义只要在 s1 的长度（l1） 这个连续的范围内，出现了 字符的数量相等并且字符数相同，就说明肯定是字串；
        // 已经不在范围内的字符 记得 清除；
        if (s1.length() > s2.length()) {
            return false;
        }

        // abc
        // xybac

        Map<Character, Integer> c1 = new HashMap<>();
        Map<Character, Integer> c2 = new HashMap<>();

        int l1 = s1.length();
        for (int i = 0; i < l1; i++) {
            c1.compute(s1.charAt(i), (key, old) -> {
                if (old == null) {
                    return 1;
                }

                return old + 1;
            });

            c2.compute(s2.charAt(i), (key, old) -> {
                if (old == null) {
                    return 1;
                }

                return old + 1;
            });
        }

        if (c1.equals(c2)) {
            return true;
        }

        // 滑动窗口
        // abc
        // xyb ac

        int start = l1;
        while (start < s2.length()) {
            c2.compute(s2.charAt(start), (key, old) -> {
                // 把新便利到的 字符 加进来；
                if (old == null) {
                    return 1;
                }

                return old + 1;
            });

            // 把前面已经滑动过的 字符 -1
            c2.compute(s2.charAt(start - l1), (key, old) -> {
                if (old - 1 == 0) {
                    return null;
                }

                return old - 1;
            });

            if (c1.equals(c2)) {
                return true;
            }

            start++;
        }

        return false;

    }
}
