package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aaron.zou
 * @date 2023/4/25 7:48 PM
 *
 * 剑指 Offer II 086. 分割回文子字符串
 * 给定一个字符串 s ，请将 s 分割成一些子串，使每个子串都是 回文串 ，返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "google"
 * 输出：[["g","o","o","g","l","e"],["g","oo","g","l","e"],["goog","l","e"]]
 * 示例 2：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 3：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 *
 *
 * 注意：本题与主站 131 题相同： https://leetcode-cn.com/problems/palindrome-partitioning/
 */
@UtilityClass
public class Off86M {

    public static void main(String[] args) {
        System.out.println(partition("google"));
        System.out.println(ret);
    }

    public String[][] partition(String s) {
        // 先动态规划预处理，找出 dp[i][j] i 到 j 每一项是否回文串.
        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        // 赋值；
        // dp[i][j] = s[i] == s[j] && (j - i < 2 || dp[i + 1][j - 1];

        // 想象 正方形， 固定住 列，然后从上往下面扫(最多扫到 i = j（中间的对角线）
        // 之所以要这么扫的原因是； dp[i][j] 这个坐标的值依赖 左下角（i + 1, j - 1)
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]);
            }
        }

        // a g o o g l e
        // dfs，遍历每一种可能性；
        // 以 a 开头;   a ag ago agoo agoog agoogl agoogle
        // 以 g 开头；  g go goo goog googl google
        // 以 o 开头；  o oo oog oogl oogle
        // 以 o 开头；  o og ogl ogle
        // 以 g 开头；  g gl gle
        // 以 l 开头；  l le
        // 以 e 开头；  e

        // 贪心； 每遇到一个 回文串，就加大字符继续往下面走

        dfs(s, 0, new ArrayList<>(), dp);

        String[][] ss = new String[ret.size()][];
        for (int i = 0; i < ret.size(); i++) {
            List<String> cur = ret.get(i);

            String[] cc = new String[cur.size()];
            for (int i1 = 0; i1 < cur.size(); i1++) {
                cc[i1] = cur.get(i1);
            }

            ss[i] = cc;
        }


        return ss;
    }

    List<List<String>> ret = new ArrayList<>();

    public void dfs(String s, int curPosition, List<String> curRet, boolean[][] dp) {
        // 本趟下来，已经到 最后一个位置了，回收结果.
        if (s.length() == curPosition) {
            ret.add(new ArrayList<>(curRet));
            return;
        }

        // 还没到最后一个，接着往后面找；
        for (int i = curPosition; i < s.length(); i++) {
            // 如果当前是回文串，接着往下面找；
            if (dp[curPosition][i]) {
                curRet.add(s.substring(curPosition, i + 1));
                dfs(s, i + 1, curRet, dp);
                curRet.remove(curRet.size() - 1);
            }
        }
    }
}
