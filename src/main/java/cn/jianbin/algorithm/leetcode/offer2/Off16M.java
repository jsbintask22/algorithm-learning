package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/5 8:53
 * <p>
 * 剑指 Offer II 016. 不含重复字符的最长子字符串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子字符串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子字符串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * <p>
 * <p>
 * 注意：本题与主站 3 题相同： https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
@UtilityClass
public class Off16M {

    public static void main(String[] args) {
        System.out.println(solution("pwwkew"));
        System.out.println(solution("abcaec"));

    }

    public int solution(String s) {
        // pwwkew;
        // 滑动窗口，从头开始一直往后面移动；
        // abcaec

        int len = s.length();
        int start = 0;
        int end = -1;

        int[] chars = new int[256];
        int max = 0;

        while (start < len) {
            if (start != 0) {
                // 不等于0，拿么 chars 中肯定已经加入了字符； 把上一个字符从中去除；
                chars[s.charAt(start - 1)] = 0;
            }

            // 检查当前字符是否 已经在 chars 里面了；
            while (end + 1 < len && chars[s.charAt(end + 1)] == 0) {
                // 不包含在里面； 加进去；
                chars[s.charAt(end + 1)] = 1;
                end++;
            }

            // 从上面出来，必然是存在重复了； 计算下最大值；
            max = Math.max(max, end - start + 1);

            start++;
        }


        return max;
    }
}
