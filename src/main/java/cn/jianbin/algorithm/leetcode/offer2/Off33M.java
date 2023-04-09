package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.*;

/**
 * @author jianbin.
 * @date 2023/4/9 9:38
 */
@UtilityClass
public class Off33M {

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(solution(strs));

        System.out.println(solution2(strs));


        System.out.println(solution(new String[]{"", ""}));

    }

    public List<List<String>> solution2(String[] strs) {
        // 解法2：利用一个 hashmap，key位每个字符串已经排好序的； values为对应的变位（
        // 注意这里每个字符可以相等的；
        Map<String, List<String>> ret = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String newKey = new String(chars);

            List<String> cur = ret.getOrDefault(newKey, new ArrayList<>());
            cur.add(str);
            ret.put(newKey, cur);
        }

        return new ArrayList<>(ret.values());
    }

        public List<List<String>> solution(String[] strs) {
        // 解法：初始一个跟 strs 同长度的 0 1 数组。 1 - 表示已是变位词，
        // 便利 strs，如果该位置 是1，则跳过； 否则去后面 找不是 1 的数字字符并且检查

        int len = strs.length;
        int[] bucket = new int[len];

        List<List<String>> ret = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            if (bucket[i] == 1) {
                continue;
            }

            // 只要当前还没被占用，那肯定能得到一个答案，先加进去再说；
            List<String> curAns = new ArrayList<>();
            curAns.add(strs[i]);
            ret.add(curAns);

            int j = i + 1;
            while (j < len) {
                // 检查 i 和 j是否异位；
                if (bucket[j] == 0) {
                    if (isAnagram(strs[i], strs[j])) {
                        // 标记 j 已经被占用；
                        bucket[j] = 1;
                        curAns.add(strs[j]);
                    }
                }

                j++;
            }
        }

        return ret;
    }

    public boolean isAnagram(String s, String t) {
        // 解法； 在判断两个字符串不相等 且 长度一致 的前提下；  再检查是否两个字符串中每个字符的次数；
        if (s.length() != t.length()) {
            return false;
        }

        int[] table = new int[256];
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            table[aChar]++;
        }

        char[] tChar = t.toCharArray();
        for (char c : tChar) {
            table[c]--;
            if (table[c] < 0) {
                // <0 说明出现了其他字符；
                return false;
            }
        }

        return true;
    }
}
