package cn.jianbin.algorithm.sort;

import lombok.SneakyThrows;

import java.util.Scanner;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            System.out.println(m1(scanner.nextLine()));
        }
    }


    /**
     * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
     * <p>
     * 如，输入： Type 输出： epTy
     * <p>
     * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
     * <p>
     * 如，输入： BabA 输出： aABb
     * <p>
     * 规则 3 ：非英文字母的其它字符保持原来的位置。
     * <p>
     * <p>
     * 如，输入： By?e 输出： Be?y
     */
    public static String m1(String src) {
        char[] chars = src.toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 26; i++) {
            char c = (char) ('A' + i);
            for (char a : chars) {
                // 等于小写或者大写
                if (c == a || a == (char) (c + 32))  {
                    sb.append(a);
                }
            }
        }

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (!(c >= 'a' && c <= 'z') && !(c >= 'A' && c <= 'Z')) {
                sb.insert(i, c);
            }
        }

        return sb.toString();

    }



}
