package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jianbin.
 * @date 2023/4/16 14:17
 * <p>
 * 剑指 Offer II 063. 替换单词
 * 在英语中，有一个叫做 词根(root) 的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * <p>
 * 现在，给定一个由许多词根组成的词典和一个句子，需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * <p>
 * 需要输出替换之后的句子。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 示例 2：
 * <p>
 * 输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * 输出："a a b c"
 * 示例 3：
 * <p>
 * 输入：dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
 * 输出："a a a a a a a a bbb baba a"
 * 示例 4：
 * <p>
 * 输入：dictionary = ["catt","cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 示例 5：
 * <p>
 * 输入：dictionary = ["ac","ab"], sentence = "it is abnormal that this solution is accepted"
 * 输出："it is ab that this solution is ac"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写字母组成。
 * 1 <= sentence.length <= 10^6
 * sentence 仅由小写字母和空格组成。
 * sentence 中单词的总量在范围 [1, 1000] 内。
 * sentence 中每个单词的长度在范围 [1, 1000] 内。
 * sentence 中单词之间由一个空格隔开。
 * sentence 没有前导或尾随空格。
 * <p>
 * <p>
 * 注意：本题与主站 648 题相同： https://leetcode-cn.com/problems/replace-words/
 */
@UtilityClass
public class Off63M {

    public static void main(String[] args) {
        List<String> dics = new ArrayList<>();
        dics.add("cat");
        dics.add("ca");


        System.out.println(replaceWords(dics, "i'm a cattle and a cat."));
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        // 解法1：把词根加到 set 中，
        // 然后遍历 sentence 中每个单词，将每个单词 从 1 - len 长度全部取一边，检查
        // 是否出现在  set 中，出现了，直接替换；

        Set<String> dicSet = new HashSet<>(dictionary);

        String[] words = sentence.split(" ");

        for (int k = 0; k < words.length; k++) {
            int len = words[k].length();

            for (int i = 0; i < len; i++) {
                if (dicSet.contains(words[k].substring(0, i + 1))) {
                    words[k] = words[k].substring(0, i + 1);
                    break;
                }
            }
        }

        return String.join(" ", words);
    }
}
