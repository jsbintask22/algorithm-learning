package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.LinkedList;

/**
 * @author jianbin.
 * @date 2023/4/9 11:09
 *
 *
 * 剑指 Offer II 036. 后缀表达式
 * 根据 逆波兰表示法，求该后缀表达式的计算结果。
 *
 * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 *
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *
 *
 * 示例 1：
 *
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 示例 2：
 *
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * 示例 3：
 *
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：
 * 该算式转化为常见的中缀算术表达式为：
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 *
 * 提示：
 *
 * 1 <= tokens.length <= 104
 * tokens[i] 要么是一个算符（"+"、"-"、"*" 或 "/"），要么是一个在范围 [-200, 200] 内的整数
 *
 *
 * 逆波兰表达式：
 *
 * 逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
 *
 * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
 * 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
 * 逆波兰表达式主要有以下两个优点：
 *
 * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
 *
 *
 * 注意：本题与主站 150 题相同： https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
@UtilityClass
public class Off36M {


    public static void main(String[] args) {
       // System.out.println(solution(new String[]{"4","13","5","/","+"}));

        System.out.println(solution(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"}));



    }

    public int solution(String[] tokens) {
        // 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
        //该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * )

        // 遇到数字 压栈，操作符 出栈，并且将计算结果再次入栈；

        LinkedList<Integer> stack = new LinkedList<>();

        for (String token : tokens) {
            boolean isNumber = isNumber(token);

            if (isOperator(token)) {
                // 出栈；
                // 先入栈的 后出来；
                Integer v2 = stack.pop();
                Integer v1 = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(v1 + v2);
                        break;
                    case "-":
                        stack.push(v1 - v2);

                        break;
                    case "*":
                        stack.push(v1 * v2);
                        break;
                    default:
                        stack.push(v1 / v2);
                        break;
                }
            } else if (isNumber) {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private boolean isNumber(String s) {
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if ('-' == aChar) {
                continue;
            }
            if (aChar < '0' || aChar > '9') {
                return false;
            }
        }

        return true;
    }

    private boolean isOperator(String s) {
        return "*".equals(s) || "/".equals(s) || "+".equals(s) || "-".equals(s);
    }
}
