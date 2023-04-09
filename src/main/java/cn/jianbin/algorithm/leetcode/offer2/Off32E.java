package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/9 9:08
 *
 * 剑指 Offer II 032. 有效的变位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断它们是不是一组变位词（字母异位词）。
 *
 * 注意：若 s 和 t 中每个字符出现的次数都相同且字符顺序不完全相同，则称 s 和 t 互为变位词（字母异位词）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "a", t = "a"
 * 输出: false
 *
 *
 * 提示:
 *
 * 1 <= s.length, t.length <= 5 * 104
 * s and t 仅包含小写字母
 *
 *
 * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 *
 *
 *
 * 注意：本题与主站 242 题相似（字母异位词定义不同）：https://leetcode-cn.com/problems/valid-anagram/
 */

@UtilityClass
public class Off32E {


    public static void main(String[] args) {

    }

    public boolean solution(String s, String t) {
        // 解法； 在判断两个字符串不相等 且 长度一致 的前提下；  再检查是否两个字符串中每个字符的次数；
        if (s.length() != t.length() || s.equals(t)) {
            return false;
        }

        int[] table = new int[256];
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            table[aChar]++;
        }

        char[] tChar = t.toCharArray();
        for (char c : tChar) {
            table[c]--;
            if (table[c] < 0) {
                // <0 说明出现了其他字符；
                return false;
            }
        }

        return true;
    }
}
