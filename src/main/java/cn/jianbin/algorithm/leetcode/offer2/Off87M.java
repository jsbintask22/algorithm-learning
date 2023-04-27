package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aaron.zou
 * @date 2023/4/26 8:17 PM
 *
 * 剑指 Offer II 087. 复原 IP
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 *
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 *
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 *
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 *
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 *
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 *
 * 输入：s = "10203040"
 * 输出：["10.20.30.40","102.0.30.40","10.203.0.40"]
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 3000
 * s 仅由数字组成
 *
 *
 * 注意：本题与主站 93 题相同：https://leetcode-cn.com/problems/restore-ip-addresses/
 */
@UtilityClass
public class Off87M {

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525011135"));
    }

    public List<String> restoreIpAddresses(String s) {
        // 解法； dfs；
        // ip限制，每段 都必须  0 <= x <= 255，并且不能包含前导 0 ，比如 01
        // 每调用 一次方法，就选取 一截 字符串，判断是否满足，如果满足，接着往下面走（不用管下面的满不满足）

        dfs(s, 0, new ArrayList<>());

        return ret;
    }

    List<String> ret = new ArrayList<>();

    public void dfs(String s, int curPosition, List<String> curIp) {
        // 退出条件； 如果 段数已经满了；
        if (curIp.size() == 4) {
            // 并且字符串也 遍历完了；
            if (curPosition == s.length()) {
                // 说明符合条件；

                ret.add(String.join(".", curIp));
            }

            return;
        }

        // 如果段数没满，但是 长度已经到了，也要退出了.
        if (s.length() == curPosition) {
            return;
        }

        // 如果当前字符 是 '0'，那它只能跟自己组
        if (s.charAt(curPosition) == '0') {
            curIp.add("0");
            dfs(s, curPosition + 1, curIp);
            curIp.remove(curIp.size() - 1);
            return;
        }

        // 第一个字符不是 '0', 比如   '1'，  那就有可能是  1, 12, 123;
        int end = curPosition + 1;

        while (end <= s.length()) {
            String seg = s.substring(curPosition, end);
            // 无效数字，那也不能继续往下面扩展了。 比如 256，接着往下面加没意义了.
            if (!isValid(seg)) {
                break;
            }

            // 是有效数字；
            curIp.add(seg);
            dfs(s, end, curIp);
            curIp.remove(curIp.size() - 1);

            end++;
        }
    }

    private boolean isValid(String s) {
        int val = Integer.parseInt(s);

        return s.length() == 1 || (!s.startsWith("0") && (val >= 0 && val <= 255));
    }


}
