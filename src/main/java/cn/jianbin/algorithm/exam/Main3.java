package cn.jianbin.algorithm.exam;

import java.util.Scanner;

/**
 * @author jianbin
 * @date 2020/8/22 17:52
 */
public class Main3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = null;
        while ((s1 = scanner.nextLine()) != null ) {
            System.out.println(test2(Integer.parseInt(s1), scanner.nextLine()));
        }
    }

    // 找出最长元音 子字符串
    public static int test(int flaw, String str) {
        char[] chars = str.toLowerCase().toCharArray();
        int length = chars.length;

        // dp表示 包含 瑕疵度  flaw 的 最长字串长度
        int[][] dp = new int[length + 1][length + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp.length; j++) {

                // 如果不是元音
                if (dp[i - 1][j - 1] == flaw) {
                    dp[i][j] = flaw;
                } else {
                    if (!isTest(chars[j])) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 0;
                    }
                }

            }
        }


        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp.length; j++) {

            }
        }

        return 0;
    }

    public static int test2(int flaw, String str) {
        char[] chars = str.toCharArray();
        int f = 0;
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (!isTest(aChar)) {
                f = f +1;
            } else {

            }
            int k = i;
            while (k != 0) {
                if (!isTest(chars[k])) {
                    k++;
                }
            }

            if (f >= flaw ) {
                max = i;
            }
        }

        return max;
    }

    public static boolean isTest(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
