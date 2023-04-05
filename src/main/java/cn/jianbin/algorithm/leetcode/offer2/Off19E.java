package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/5 20:04
 *
 * 给定一个非空字符串 s，请判断如果 最多 从字符串中删除一个字符能否得到一个回文字符串。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "aba"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "abca"
 * 输出: true
 * 解释: 可以删除 "c" 字符 或者 "b" 字符
 * 示例 3:
 *
 * 输入: s = "abc"
 * 输出: false
 *  
 *
 * 提示:
 *
 * 1 <= s.length <= 105
 * s 由小写英文字母组成
 *  
 *
 * 注意：本题与主站 680 题相同： https://leetcode-cn.com/problems/valid-palindrome-ii/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/RQku0D
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@UtilityClass
public class Off19E {
    public static void main(String[] args) {
        System.out.println(solution("abca"));
        System.out.println(solution("cbbcc"));

        System.out.println(solution("cbdkbcc"));
    }


    public boolean solution(String s) {
        // 定义 两个指针，分别从 头、尾 开始，一直到相遇； 检查是否这个过程中 字符相等；
        // a aa
        // "  "

        int left = 0;
        int right = s.length() - 1;

        boolean first = true;
        while (left < right) {
            // 检查是否有效字符；
            while (!isValid(s.charAt(left)) && left < right) {
                left++;
            }

            while (!isValid(s.charAt(right)) && left < right) {
                right--;
            }

            if (toUpper(s.charAt(left)) != toUpper(s.charAt(right))) {
                return sub(s, left + 1, right) || sub(s, left, right - 1);
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

    private boolean sub(String s, int left, int right) {
        // 检查是否局部 回文 串  cbbcc
        if (left == right) {
            return true;
        }

        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }

        return true;
    }
}
