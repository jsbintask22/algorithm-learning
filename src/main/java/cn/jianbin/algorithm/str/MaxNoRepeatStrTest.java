package cn.jianbin.algorithm.str;

import lombok.SneakyThrows;

import java.util.Scanner;

/**
 * @author jianbin
 * @date 2020/9/3 20:31
 */
public class MaxNoRepeatStrTest {
    @SneakyThrows
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            print(scanner.nextLine());
        }
    }

    public static void print(String str) {
        char[] chars = str.toCharArray();

        int max = 0;
        int start = 0;
        for (int i = 0; i < chars.length; i++) {

            boolean flag = true;
            while (flag) {
                int[] count = new int[256];
                int endIndex = i + max;
                if (endIndex > chars.length) {
                    break;
                }
                char[] subChars = str.substring(i, endIndex).toCharArray();
                for (int j = 0; j < subChars.length; j++) {
                    char c = subChars[j];
                    if (count[c] == 0) {
                        count[c] = 1;
                    } else {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    start = i;
                    max++;
                }
            }
        }

        System.out.println(str.substring(start, start + max - 1));
    }
}
