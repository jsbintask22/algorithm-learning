package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/16 15:50
 */
@UtilityClass
public class Off64M {

    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();

        String[] dics = new String[]{"hello", "leetcode"};
        magicDictionary.buildDict(dics);

        System.out.println(magicDictionary.search("hello")); // 返回 False
        System.out.println(magicDictionary.search("hhllo")); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
        System.out.println(magicDictionary.search("hell")); // 返回 False
        System.out.println(magicDictionary.search("leetcoded")); // 返回 False

    }


    public static class MagicDictionary {
        Trie root = new Trie();

        /**
         * Initialize your data structure here.
         */
        public MagicDictionary() {

        }

        public void buildDict(String[] dictionary) {
            for (String s : dictionary) {
                Trie cur = root;

                char[] chars = s.toCharArray();

                for (char aChar : chars) {
                    int idx = aChar - 'a';

                    if (cur.nextTrie[idx] == null) {
                        cur.nextTrie[idx] = new Trie();
                    }
                    cur = cur.nextTrie[idx];
                }
                cur.endCounter++;
            }
        }

        public boolean search(String searchWord) {
            return search0(searchWord, 0, root, false);
        }

        private boolean search0(String word, int curPos, Trie curTrie, boolean modified) {
            if (curPos == word.length()) {
                // 已经是最后一个字母了，并且前面已经替换过一次了； 返回true
                return modified;
            }


            char curChar = word.charAt(curPos);
            // 检查当前字符是否出现在 字典里面；
            int idx = curChar - 'a';
            if (curTrie.nextTrie[idx] != null) {
                // 出现过。 检查下面的;
                boolean nextRet = search0(word, curPos + 1, curTrie.nextTrie[idx], modified);
                if (nextRet) {
                    // 结果通过了，直接返回true
                    return true;
                }
            }

            // 没出现过 OR 上面回溯以后还是 false， 替换当前字符 再试一下；
            for (int i = 0; i < 26; i++) {
                // 不能替换为 一样的字符 'a' -> 'a'
                // 并且 替换的单词 是要已经在 curTrie 中存在的；
                if (!modified) {
                    // 之前没换过，现在可以换.
                    if (idx != i && curTrie.nextTrie[i] != null) {
                        boolean curRet = search0(word, curPos + 1, curTrie.nextTrie[i], true);

                        if (curRet) {
                            return true;
                        }
                    }
                }
            }

            return false;
        }
    }

    public static class Trie {
        int endCounter;

        Trie[] nextTrie;

        public Trie() {
            endCounter = 0;
            nextTrie = new Trie[26];
        }
    }
}
