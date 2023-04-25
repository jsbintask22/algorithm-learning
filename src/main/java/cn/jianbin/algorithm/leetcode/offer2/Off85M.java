package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author aaron.zou
 * @date 2023/4/25 7:01 PM
 *
 * 剑指 Offer II 085. 生成匹配的括号
 * 正整数 n 代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 *
 * 提示：
 *
 * 1 <= n <= 8
 *
 *
 * 注意：本题与主站 22 题相同： https://leetcode-cn.com/problems/generate-parentheses/
 */
@UtilityClass
public class Off85M {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        // 解法； 回溯
        // 假如 栈是空的，只能放 "左" ，
        // 假如 不为空， 则有两种选择，"左"、"右"；
        // 前提： 放左括号要确保还有 "左"， 放"右" 括号则要确保 栈弹出来的 是 "左括号"

        dfs(new StringBuilder(), n, n);
        return ret;
    }

    List<String> ret = new ArrayList<>();
    public void dfs(StringBuilder curPath, int leftNum, int rightNum) {
        // 终止条件. 右 括号空了
        if (rightNum == 0) {
            ret.add(curPath.toString());
            return;
        }


        // 左右括号 都不为空. 既可以放左，也可以放右，但是放右的前提是 rightNum > leftNum;
        if (leftNum > 0) {
            curPath.append("(");
            dfs(curPath, leftNum - 1, rightNum);
            curPath.deleteCharAt(curPath.length() - 1);
        }

        // 右右括号的前提是  rightNum > leftNum（确保前面有一个 左括号在等着）
        if (rightNum > leftNum) {
            curPath.append(")");
            dfs(curPath, leftNum, rightNum - 1);
            curPath.deleteCharAt(curPath.length() - 1);
        }

    }
}
