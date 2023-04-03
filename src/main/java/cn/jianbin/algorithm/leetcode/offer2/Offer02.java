package cn.jianbin.algorithm.leetcode.offer2;

/**
 * @author jianbin.
 * @date 2023/4/1 16:43
 *
 *
 * 剑指 Offer II 002. 二进制加法
 * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "10"
 * 输出: "101"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *   10101
 *    1011
 *  100000
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *
 */
public class Offer02 {

    public static void main(String[] args) {
        System.out.println(solution("10101", "1011"));

        System.out.println(solution("10101", "11"));

        System.out.println(solution("1111", "1111"));


    }

    public static String solution(String a, String b) {
        // 有任何一个字符串为 0，就返回另一个；
        if ("0".equals(a)) {
            return b;
        }

        if ("0".equals(b)) {
            return a;
        }

        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();

        int al = as.length - 1;
        int bl = bs.length - 1;

        StringBuilder ret = new StringBuilder();

        int last = 0;
        while (al >= 0 || bl >= 0) {
            int av = al >= 0 ? as[al--] - '0' : 0;
            int bv = bl >= 0 ? bs[bl--] - '0' : 0;

            int cur = av + bv + last;
            last = cur >= 2 ? 1 : 0;

            ret.append(cur % 2);
        }

        if (last > 0) {
            ret.append(last);
        }

        return ret.reverse().toString();
    }
}
