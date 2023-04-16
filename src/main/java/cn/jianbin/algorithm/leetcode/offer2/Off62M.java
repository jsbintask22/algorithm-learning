package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/16 13:43
 *
 * 剑指 Offer II 062. 实现前缀树
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *
 *
 * 示例：
 *
 * 输入
 * inputs = ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * inputs = [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 *
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *
 *
 * 提示：
 *
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 *
 *
 *
 *
 * 注意：本题与主站 208 题相同：https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
@UtilityClass
public class Off62M {

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("hello");
        trie.insert("abc");
        trie.insert("abk");

        System.out.println(trie.search("hello"));

        System.out.println(trie.startsWith("ab"));
        System.out.println(trie.search("abc"));

        System.out.println(trie.search("hell"));
    }

    public static class Trie {

        /**
         * 解法； 字典数；
         *
         * 声明 PreNode 数据结构，包含 每个单词的字符通过的次数，以当前作为结尾的次数，
         * 以及下一个节点的 数组；
         */
        PreNode root = new PreNode();

        /** Initialize your data structure here. */
        public Trie() {

        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            char[] chars = word.toCharArray();

            PreNode cur = root;

            for (char aChar : chars) {
                int idx = aChar - 'a';

                if (cur.nextNodes[idx] == null) {
                    // 没找到，说明还没初始化；
                    cur.nextNodes[idx] = new PreNode();
                }

                cur.nextNodes[idx].passCounter++;
                cur = cur.nextNodes[idx];
            }

            // 从上面出来以后，当前就是结尾了；
            cur.endCounter++;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            char[] chars = word.toCharArray();

            PreNode cur = root;
            for (char aChar : chars) {
                int idx = aChar - 'a';

                if (cur.nextNodes[idx] == null) {
                    // 没有通过这里，说明没有存储过.
                    return false;
                }

                cur = cur.nextNodes[idx];
            }

            if (cur.endCounter == 0) {
                return false;
            }

            return true;
        }

        public void remove(String word) {
            if (!search(word)) {
                return;
            }

            // 在已经存在的情况下，干掉每一个字符；
            PreNode cur = root;
            char[] chars = word.toCharArray();
            for (char aChar : chars) {
                int idx = aChar - 'a';

                if (--cur.nextNodes[idx].passCounter == 0) {
                    // 减完以后，可能等于 0 了（没有其它单词了），直接干掉，后面不用管了（已经确保 word 肯定在这个路径上了）
                    cur.nextNodes[idx] = null;
                    return;
                }
                cur = cur.nextNodes[idx];
            }

            cur.endCounter--;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();

            PreNode cur = root;
            for (char aChar : chars) {
                int idx = aChar - 'a';

                if (cur.nextNodes[idx] == null) {
                    // 没有通过这里，说明没有存储过.
                    return false;
                }

                cur = cur.nextNodes[idx];
            }

            return true;
        }



        public static class PreNode {
            int passCounter;
            int endCounter;

            private PreNode[] nextNodes;

            public PreNode() {
                passCounter = 0;
                endCounter = 0;

                /**
                 * 这个节点被通过了几次了， 有几次拿当前节点作为 末尾；
                 * 下一个节点在哪里；
                 */
                nextNodes = new PreNode[26];
            }
        }
    }

}
