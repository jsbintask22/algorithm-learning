package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jianbin.
 * @date 2023/4/16 17:47
 *
 * 剑指 Offer II 065. 最短的单词编码
 * 单词数组 words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，且满足：
 *
 * words.length == indices.length
 * 助记字符串 s 以 '#' 字符结尾
 * 对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（但不包括 '#'）的 子字符串 恰好与 words[i] 相等
 * 给定一个单词数组 words ，返回成功对 words 进行编码的最小助记字符串 s 的长度 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：words = ["time", "me", "bell"]
 * 输出：10
 * 解释：一组有效编码为 s = "time#bell#" 和 indices = [0, 2, 5] 。
 * words[0] = "time" ，s 开始于 indices[0] = 0 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
 * words[1] = "me" ，s 开始于 indices[1] = 2 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
 * words[2] = "bell" ，s 开始于 indices[2] = 5 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
 * 示例 2：
 *
 * 输入：words = ["t"]
 * 输出：2
 * 解释：一组有效编码为 s = "t#" 和 indices = [0] 。
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * words[i] 仅由小写字母组成
 *
 *
 * 注意：本题与主站 820 题相同： https://leetcode-cn.com/problems/short-encoding-of-words/
 */
@UtilityClass
public class Off65M {

    public static void main(String[] args) {

    }

    public int minimumLengthEncoding(String[] words) {
        // 解法；直接使用一个  set 存储每个单词，然后如果 某个单词是另一个单词的后缀
        // 则它一定已经包含在里面了；
        Set<String> set = new HashSet<>(Arrays.asList(words));

        // time me bell
        for (String word : words) {
            int i = 1;
            while (i < word.length()) {
                set.remove(word.substring(i));
                i++;
            }
        }

        int ret = 0;
        for (String s : set) {
            ret += s.length() + 1;
        }

        return ret;
    }
}
