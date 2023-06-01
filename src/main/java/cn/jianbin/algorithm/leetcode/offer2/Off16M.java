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
        System.out.println(solution("tmmzuxt"));

        System.out.println(solution("dvdf"));
        System.out.println(solution("pawwkew"));
        System.out.println(solution("abcaec"));


    }

    public int solution(String s) {
        // pwwkew;
        // 滑动窗口，从头开始一直往后面移动；
        // abcaec

        int len = s.length();
        int start = 0;
        int end = 0;

        // chars 用于存储 start - end 这个窗口期间生成的 字符； 用于判断是否已经出现过了.
        int[] store = new int[256];
        int max = 0;

        // pwwkew
        // abc
        while (end < len) {
            // end 一直往后面走，直到出现重复字符了.
            while (end < len && (store[s.charAt(end)] == 0)) {
                // ==0， 说明一直没有重复，一直+; 并且一直存储这期间遇到的字符
                // 没有重复，把当前字符的位置计入；  这里之所以要 + 1 是因为我们用的 == 0 判断之前是否出现过；
                store[s.charAt(end)] = end + 1;
                end++;
            }

            // 从上面出来，要么重复，要么 end 已经到末尾了. 不管哪种情况直接计算 最大长度；
            max = Math.max(max, end - start);

            // 如果是遇到重复了。把之前加进去的干掉.
            if (end < len) {
                // 干掉后还要重置 start 指针；
                // 这里关键在于要滑动到上一个 重复字符出现的位置 + 1
                // 这里要跟现有的 start 进行一次比较是怕  滑动回了比 start 还小的位置； 这是不符合规定的（必须连续）
                // 比如 abba   start 在b2 位置，然后a出现， 直接滑动回了 a0;  那最大值就变成了   5 - 1 = 4
                start = Math.max(store[s.charAt(end)], start);

                // 然后把之前重复的字符 删掉（置位0）
                store[s.charAt(end)] = 0;
            }
        }


        return max;
    }
}
