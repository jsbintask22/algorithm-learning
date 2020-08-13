package cn.jianbin.algorithm.number;

import java.util.Scanner;

public class NumEncryptTest {
    public static final char[] a_z = "abcdefghijklmopqrstuvwsyz".toCharArray();
    public static final char[] A_Z = "abcdefghijklmopqrstuvwsyz".toUpperCase().toCharArray();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        System.out.println(encry(s));
    }

    public static String encry(String s) {
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char a = chars[i];
            if (a == 'z') {
                sb.append("A");
                continue;
            } else if (a == 'Z') {
                sb.append("a");
                continue;
            } else if (a == 9) {
                sb.append("0");
                continue;
            }

            int type = getType(a);
            if (type == 1) {
                sb.append((char) (a - 31));
            } else if (type == 2) {
                sb.append((char) (a + 33));
            } else if (type == 3) {
                sb.append((char) (a + 1));
            } else {
                throw new IllegalArgumentException("illegal arg:" + a);
            }

        }

        return sb.toString();
    }



    public static int getType(int c) {
        if (c >= 'a' && c <= 'z') {
            return 1;
        }
        if (c >= 'A' && c <= 'Z') {
            return 2;
        }

        try {
            Integer.parseInt(c + "");
            return 3;
        } catch (Exception e) {

        }

        return -1;
    }
}
