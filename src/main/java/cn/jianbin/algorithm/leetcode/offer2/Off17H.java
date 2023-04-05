package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/5 15:10
 * <p>
 * 剑指 Offer II 017. 含有所有字符的最短字符串
 * 给定两个字符串 s 和 t 。返回 s 中包含 t 的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。
 * <p>
 * 如果 s 中存在多个符合条件的子字符串，返回任意一个。
 * <p>
 * <p>
 * <p>
 * 注意： 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最短子字符串 "BANC" 包含了字符串 t 的所有字符 'A'、'B'、'C'
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3：
 * <p>
 * 输入：s = "a", t = "aa"
 * 输出：""
 * 解释：t 中两个字符 'a' 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 * <p>
 * <p>
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 * <p>
 * <p>
 * <p>
 * 注意：本题与主站 76 题相似（本题答案不唯一）：https://leetcode-cn.com/problems/minimum-window-substring/
 */
@UtilityClass
public class Off17H {

    public static void main(String[] args) {
        System.out.println(solution("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));

    }

    public static String solution(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        int tLen = t.length();
        int sLen = s.length();

        // 初始化一个 字符数组，进行第一轮比较
        int[] tChars = new int[256];
        for (int i = 0; i < tLen; i++) {
            tChars[t.charAt(i)]++;
            tChars[s.charAt(i)]--;
        }

        int diff = 0;
        for (int tChar : tChars) {
            if (tChar > 0) {
                // 大于0，说明没有消除干净；
                diff++;
            }
        }

        if (diff == 0) {
            // 第一轮就满足了； 直接返回；
            return t;
        }

        int start = 0, end = tLen, lmin = 0, rmin = sLen;

        // 能够走到这里来的，说明 diff 铁定大于0；  不满足
        while (end < sLen) {
            // 先往右滑动，找出一个满足的窗口； diff 肯定大于0
            tChars[s.charAt(end)]--;

            if (tChars[s.charAt(end)] == 0) {
                // 等于0； 说明本次 恰好抵消掉了，diff - 1
                diff--;
            }

            if (diff != 0) {
                // 抵消完了以后，检查是否已经找到了这么一个窗口；
                end++;
                continue;
            }

            // 走到这里了。 说明 diff = 0，已经抵消完了；
            // 开始尝试 从左边 缩窗口；

            for (; diff == 0; start++) {
                tChars[s.charAt(start)]++;

                if (tChars[s.charAt(start)] == 1) {
                    // == 1，说明有一个 目标元素被新加进来了， diff++；
                    diff++;
                }
            }

            // 从上面出来以后，说明 diff > 0，start 已经缩到了一个不满足的 位置(但是前一个是满足的）
            // 所以检查前一个 满足的 位置和 end 是否最小窗口；
            if (end - start + 1 < rmin - lmin) {
                rmin = end;
                lmin = start - 1;
            }
        }

        return s.substring(lmin, rmin + 1);
    }

    public String minWindow(String s, String t) {
        int ns = s.length(), nt = t.length();
        if (ns < nt) return "";
        int[] hash = new int[58]; // A~Z: 65~90, a~z: 97~122
        int diff = 0;
        for (int i = 0; i < nt; i++) {
            hash[t.charAt(i) - 'A']++;
            hash[s.charAt(i) - 'A']--;
        }
        for (int val : hash) { // 只关心未抵消的字符
            if (val > 0) diff++;
        }
        if (diff == 0) return s.substring(0, nt); // 第一个窗为最小覆盖子串时
        int l = 0, r = nt, lmin = 0, rmin = ns;
        for (; r < ns; r++) { // 只要当前窗还未覆盖，向右侧扩窗
            int in = s.charAt(r) - 'A';
            hash[in]--;
            if (hash[in] == 0) diff--; // in入窗后使得窗内该字符个数与t中相同
            if (diff != 0) continue; // diff不为0则继续扩窗
            for (; diff == 0; l++) { // 从左侧缩窗
                int out = s.charAt(l) - 'A';
                hash[out]++;
                if (hash[out] == 1) diff++;
            }
            if (r - l + 1 < rmin - lmin) { // 缩窗后得到一个合格窗，若窗宽更小，更新窗界
                lmin = l - 1;
                rmin = r;
            }
        }
        return rmin == ns ? "" : s.substring(lmin, rmin + 1); // 根据窗界是否有过更新来返回相应的结果
    }

    private int[] copy(int[] arr) {
        int[] c = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            c[i] = arr[i];
        }

        return c;
    }
}
