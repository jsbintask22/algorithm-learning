package cn.jianbin.algorithm.leetcode.top100;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * @author jianbin.
 * @date 2023/8/13 10:50
 */
public class T394M {

    public static void main(String[] args) {
        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("3[a2[c]]"));

    }

    public static String decodeString(String s) {
        // 准备一个栈，遇到 数字，全部解析出来，遇到 字符 OR [  括号，加入；
        // 遇到 ] 把栈里面的全部解析出来，然后拼接字符串后反转；

        LinkedList<String> stack = new LinkedList();

        int ptr = 0;
        String ret = "";

        while (ptr < s.length()) {
            char c = s.charAt(ptr);

            // 2[a2[bc]]
            if (Character.isDigit(c)) {
                // 数字，整个数字字符入栈；
                String times = "";
                while (Character.isDigit(s.charAt(ptr))) {
                    times += s.charAt(ptr);
                    ptr++;
                }

                stack.push(times);
            } else if (Character.isLetter(c) || c == '[') {
                // 如果是 字符 OR [ 则入栈；

                stack.push(String.valueOf(c));
                ptr++;
            } else {
                // 最后，如果遇到了 ] ，则要开始出栈了；

                //
                String curStr = "";

                ptr++;

                // 一直出栈，直到遇到 [
                while (!"[".equals(stack.peek())) {
                    curStr = stack.pop() + curStr;
                }

                // curStr == cc
                // 3[a2[c]]
                // curStr =

                // 把 [ 跳过去；
                stack.pop();

                // 找到数字（肯定是数字）
                int times = Integer.parseInt(stack.pop());

                String temp = "";
                while (times-- > 0) {
                    temp += curStr;
                }

                stack.add(temp);
            }
        }



        return String.join("", stack);
    }
}
