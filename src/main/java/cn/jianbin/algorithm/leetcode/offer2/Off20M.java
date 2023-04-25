package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/5 20:30
 *
 * 剑指 Offer II 020. 回文子字符串的个数
 * 给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 *
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 *
 *
 * 注意：本题与主站 647 题相同：https://leetcode-cn.com/problems/palindromic-substrings/
 *
 */
@UtilityClass
public class Off20M {

    public static void main(String[] args) {
        System.out.println(solution("aaa"));

        System.out.println(solution("abc"));
    }

    public int solution(String s) {
        // 思路； 遍历每一个位置，以该位置 i 为回文串的中心，往两边不断扩展（必须同时，这样 i 才是中心）
        // 中心点也可能是 两个字符； i,i + 1;

        int start = 0;
        int len = s.length();
        int max = 0;

        while (start < len) {
            // 回文串中心 可以为 一个点 也可以为 两个点(偶数）
            max += countPalindrome(s, start, start);
            max += countPalindrome(s, start, start + 1);
            start++;
        }

        return max;
    }

    private int countPalindrome(String s, int left, int right) {
        // 统计以 left，right 为中心可得到多少个回文串；
        int ret = 0;
        int len = s.length();

        while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
            ret++;
            left--;
            right++;
        }

        return ret;
    }

}
