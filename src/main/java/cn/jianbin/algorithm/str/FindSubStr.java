package cn.jianbin.algorithm.str;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;

@UtilityClass
public class FindSubStr {

    /**
     * 求同源异构字符串出现的 初始索引位置
     * Str:    abcafgkabcc
     * sub:     accb
     * 结果：    abcc 匹配  accb
     *
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {
        // 暴力枚举
        String str = "abcafgkabcc";
        String sub = "accb";

        System.out.println(slideWindow1(str, sub));


        System.out.println(isCountEquals("abcc", "accb"));
        System.out.println(isCountEquals("accd", "accb"));


        System.out.println("中文abc*6^".toCharArray().length);


        for (int i = 0; i < 10000; i++) {
            String o = RandomStringUtils.randomAscii(5);
            String s = RandomStringUtils.randomAscii(2);

            if (slideWindow1(o, s) != -1) {
                System.err.println(o + "\n" + s);
            }
        }

    }

    /**
     * 滑动 窗口法
     *
     * @param str
     * @param sub
     * @return
     */
    public int slideWindow1(String str, String sub) {
        if (str.length() < sub.length()) {
            return -1;
        }

        char[] subChars = sub.toCharArray();
        Arrays.sort(subChars);

        for (int i = 0; i < str.length() - sub.length() + 1; i++) {
            char[] subStrChars = str.substring(i, i + sub.length()).toCharArray();
            Arrays.sort(subStrChars);

            if (Arrays.equals(subChars, subStrChars)) {
                return i;
            }
        }

        return -1;
    }


    /**
     * 计数法
     */
    public boolean isCountEquals(String str, String sub) {
        if (str.length() != sub.length()) {
            return false;
        }

        char[] subChars = sub.toCharArray();
        int[] count = new int[256];

        for (int i = 0; i < subChars.length; i++) {
            count[subChars[i]]++;
        }

        char[] strChars = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (count[strChars[i]] == 0) {
                return false;
            }

            count[strChars[i]]--;
        }

        return true;
    }
}
