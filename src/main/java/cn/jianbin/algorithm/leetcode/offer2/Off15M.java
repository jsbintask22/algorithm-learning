package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.*;

/**
 * @author jianbin.
 * @date 2023/4/5 8:34
 * <p>
 * 剑指 Offer II 015. 字符串中的所有变位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 变位词 指字母相同，但排列不同的字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的变位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的变位词。
 * 示例 2：
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的变位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的变位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的变位词。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 * <p>
 * <p>
 * 注意：本题与主站 438 题相同： https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 */
@UtilityClass
public class Off15M {


    public static void main(String[] args) {
        System.out.println(solution("cbaebabacd", "abc"));
    }

    public List<Integer>  solution(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return Collections.emptyList();
        }

        // abc
        // xybac

        Map<Character, Integer> c1 = new HashMap<>();
        Map<Character, Integer> c2 = new HashMap<>();

        int l1 = s2.length();
        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i < l1; i++) {
            c1.compute(s2.charAt(i), (key, old) -> {
                if (old == null) {
                    return 1;
                }

                return old + 1;
            });

            c2.compute(s1.charAt(i), (key, old) -> {
                if (old == null) {
                    return 1;
                }

                return old + 1;
            });
        }

        if (c1.equals(c2)) {
            ret.add(0);
        }

        // 滑动窗口
        // abc
        // xyb ac

        int start = l1;
        while (start < s1.length()) {
            c2.compute(s1.charAt(start), (key, old) -> {
                // 把新便利到的 字符 加进来；
                if (old == null) {
                    return 1;
                }

                return old + 1;
            });

            // 把前面已经滑动过的 字符 -1
            c2.compute(s1.charAt(start - l1), (key, old) -> {
                if (old - 1 == 0) {
                    return null;
                }

                return old - 1;
            });

            if (c1.equals(c2)) {
                ret.add(start - l1  +1);
            }

            start++;
        }

        return ret;

    }
}
