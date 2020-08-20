package cn.jianbin.algorithm.str;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author jianbin
 * @date 2020/8/20 19:31
 */
public class StrExpressionTest {

    @SneakyThrows
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String postExpression = preToPostExpression(scanner.nextLine());
            System.out.println(postExpression);
            System.err.println(calPostExpression(postExpression));
        }
    }

    /**
     * 1 + 20 * (3 + 4 * (1 + 3) - 2) * 1 + 3
     */
    public static String preToPostExpression(String preStr) {
        Stack<Character> opStack = new Stack<>();
        List<String> opList = new ArrayList<>();

        char[] preChars = preStr.toCharArray();
        for (int i = 0; i < preChars.length; i++) {
            char preChar = preChars[i];

            if (preChar == ' ') continue;

            // 如果是数字，入栈  一直找到下个字符不是数字的字符 截取
            if (isNum(preChar)) {
                int start = i;

                while (++i <= preChars.length - 1 && isNum(preChars[i])) {
                }
                opList.add(preStr.substring(start, i));
                // 因为前面多加了一个数， 所以 再 - 1
                i--;

                // 如果是左括号 直接入栈
            } else if (preChar == '(') {
                opStack.push(preChar);

                // 如果是右括号  将操作数栈中的操作符出栈 压入 数字栈
            } else if (preChar == ')') {
                while (opStack.peek() != '(') {
                    opList.add(String.valueOf(opStack.pop()));
                }
                // 左括号也需要丢弃
                opStack.pop();

                // 如果是运算符 + - * /
            } else {
                boolean flag = true;

                while (flag) {
                    // 如果操作数栈 顶部为 （ 或者为空  直接入栈
                    if (opStack.isEmpty() || opStack.peek() == '(') {
                        opStack.push(preChar);
                        flag = false;

                        // 如果 优先级比 栈顶高  也入栈
                    } else if (greaterThan(preChar, opStack.peek())) {
                        opStack.push(preChar);
                        flag = false;

                        // 其它情况 将栈顶 操作符 添加到 操作数栈 然后在比较
                    } else {
                        opList.add(String.valueOf(opStack.pop()));

                    }
                }
            }
        }

        // 比较完成  将操作符栈中的字符 倒叙添加到 操作数栈 然后输出
        while (!opStack.isEmpty()) {
            opList.add(String.valueOf(opStack.pop()));
        }

        return String.join("", opList);
    }

    public static String calPostExpression(String postExpression) {
        Stack<Double> resultStack = new Stack<>();

        char[] postChars = postExpression.toCharArray();
        for (int i = 0; i < postChars.length; i++) {
            char postChar = postChars[i];
            if (postChar == ' ') continue;

            // 如果是 数字 入栈
            if (isNum(postChar)) {
                int start = i;
                resultStack.push(Double.parseDouble(postExpression.substring(start, i + 1)));
            } else {
                Double v2 = resultStack.pop();
                Double v1 = resultStack.pop();
                // 不是数字  直接计算
                switch (postChar) {
                    case '+': {
                        resultStack.push(v1 + v2);
                        break;
                    }
                    case '-': {
                        resultStack.push(v1 - v2);
                        break;
                    }
                    case '*': {
                        resultStack.push(v1 * v2);
                        break;
                    }
                    case '/': {
                        resultStack.push(v1 / v2);
                        break;
                    }
                }
            }

        }

        return resultStack.pop().toString();
    }

    public static boolean isNum(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z');
    }

    public static boolean greaterThan(char c1, char c2) {
        switch (c1) {
            case '*':
                return c2 == '+' || c2 == '-';

            case '/': {
                return c2 == '+' || c2 == '-';
            }

            default: {
                return false;
            }
        }
    }
}
