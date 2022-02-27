package cn.jianbin.algorithm.tree;

import cn.hutool.core.util.RandomUtil;
import cn.jianbin.alogthrim.datastructure.PreTree;
import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2022/2/8 7:11 下午
 */
@UtilityClass
public class PreTreeTest {
    public static void main(String[] args) {
        PreTree preTree = new PreTree();
        String s1 = "abc";
        String s2 = "eac";
        String s3 = "ac";
        String s4 = "acb";
        String s5 = "abcefa";

        preTree.addWord(s1);
        preTree.addWord(s1);
        preTree.addWord(s2);
        preTree.addWord(s3);
        preTree.addWord(s4);
        preTree.addWord(s5);

        System.out.println(preTree.searchWord(s1));
        System.out.println(preTree.searchWord(s2));
        System.out.println(preTree.searchWord(s3));
        System.out.println(preTree.searchWord(s4));
        System.out.println(preTree.searchWord(s5));
        System.out.println(preTree.searchWord("cc"));
        System.out.println(preTree.searchWord("a"));

        preTree.remove(s5);
        System.out.println(preTree.searchWord(s1));
        System.out.println(preTree.searchWord(s5));
    }
}
