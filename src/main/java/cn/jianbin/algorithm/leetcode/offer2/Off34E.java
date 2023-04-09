package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/9 10:01
 *
 *剑指 Offer II 034. 外星语言是否排序
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 *
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * 输出：true
 * 解释：在该语言的字母表中，'h' 位于 'l' 之前，所以单词序列是按字典序排列的。
 * 示例 2：
 *
 * 输入：words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * 输出：false
 * 解释：在该语言的字母表中，'d' 位于 'l' 之后，那么 words[0] > words[1]，因此单词序列不是按字典序排列的。
 * 示例 3：
 *
 * 输入：words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * 输出：false
 * 解释：当前三个字符 "app" 匹配时，第二个字符串相对短一些，然后根据词典编纂规则 "apple" > "app"，因为 'l' > '∅'，其中 '∅' 是空白字符，定义为比任何其他字符都小（更多信息）。
 *
 *
 * 提示：
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 20
 * order.length == 26
 * 在 words[i] 和 order 中的所有字符都是英文小写字母。
 *
 *
 * 注意：本题与主站 953 题相同： https://leetcode-cn.com/problems/verifying-an-alien-dictionary/
 */
@UtilityClass
public class Off34E {

    public static void main(String[] args) {
        System.out.println(solution(new String[] {"hello","leetcode"}, "hlabcdefgijkmnopqrstuvwxyz"));

        System.out.println(solution(new String[] {"word","world","row"}, "worldabcefghijkmnpqstuvxyz"));

    }

    public boolean solution(String[] words, String order) {
        int len = words.length;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (compare(words[i], words[j], order) < 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public int compare(String s1, String s2, String orderr) {
        if (s1.equals(s2)) {
            return 0;
        }

        int l1 = s1.length();
        int l2 = s2.length();

        int k1 = 0;
        while (k1 < l1 || k1 < l2) {
            int size1 = k1 < l1 ? orderr.indexOf(s1.charAt(k1)) : -1;
            int size2 = k1 < l2 ? orderr.indexOf(s2.charAt(k1)) : -1;

            if (size1 == size2) {
                k1++;
                continue;
            }

            if (size1 < size2) {
                return 1;
            }

            return -1;
        }

        return 0;
    }
}
