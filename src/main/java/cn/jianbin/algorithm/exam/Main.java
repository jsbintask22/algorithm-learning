package cn.jianbin.algorithm.exam;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author jianbin
 * @date 2020/8/22 15:50
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            System.out.println(test(scanner.nextLine()));
        }
    }

    public static int test(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        int max = 0;
        int st = -1;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);

                // 下一个还是 左括号
                int k = i;

                max = Math.max(stack.size(), max);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    return 0;
                }

                // ) ] }
                Character top = stack.pop();
                if (c == ')' && top != '(') return 0;
                if (c == ']' && top != '[') return 0;
                if (c == '}' && top != '{') return 0;

            } else {
                return 0;
            }
        }


        if (!stack.isEmpty()) {
            return 0;
        }

        int temp = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            char c = chars[i];
            if (c == ')' || c == ']' || c == '}') {
                temp++;
            } else {
                break;
            }
        }

        return max;
    }
}
