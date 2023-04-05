package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/5 19:42
 * 剑指 Offer II 018. 有效的回文
 * 给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 本题中，将空字符串定义为有效的 回文串 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * 示例 2:
 *
 * 输入: s = "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 2 * 105
 * 字符串 s 由 ASCII 字符组成
 *
 *
 * 注意：本题与主站 125 题相同： https://leetcode-cn.com/problems/valid-palindrome/
 *
 * 通过次数40,368提交次数78,299
 */
@UtilityClass
public class Off18E {

    public static void main(String[] args) {
        System.out.println(solution(" "));
        System.out.println(solution("aa aa"));
        System.out.println(solution("A man, a plan, a canal: Panama"));
        System.out.println(solution("race a car"));
    }

    public boolean solution(String s) {
        // 定义 两个指针，分别从 头、尾 开始，一直到相遇； 检查是否这个过程中 字符相等；
        // a aa
        // "  "

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // 检查是否有效字符；
            while (!isValid(s.charAt(left)) && left < right) {
                left++;
            }

            while (!isValid(s.charAt(right)) && left < right) {
                right--;
            }

            if (toUpper(s.charAt(left)) != toUpper(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }


        return true;
    }

    private boolean isValid(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private char toUpper(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) (c - 32);
        }

        return c;
    }
}
