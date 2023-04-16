package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jianbin.
 * @date 2023/4/16 18:06
 *
 * 剑指 Offer II 066. 单词之和
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
 *
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 *
 *
 * 示例：
 *
 * 输入：
 * inputs = ["MapSum", "insert", "sum", "insert", "sum"]
 * inputs = [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * 输出：
 * [null, null, 3, null, 5]
 *
 * 解释：
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 *
 *
 * 提示：
 *
 * 1 <= key.length, prefix.length <= 50
 * key 和 prefix 仅由小写英文字母组成
 * 1 <= val <= 1000
 * 最多调用 50 次 insert 和 sum
 *
 *
 * 注意：本题与主站 677 题相同： https://leetcode-cn.com/problems/map-sum-pairs/
 */
@UtilityClass
public class Off66M {
    public static void main(String[] args) {

    }

    public static class MapSum {
        Map<String, Integer> map = new HashMap<>();
        Node root;

        /** Initialize your data structure here. */
        public MapSum() {
            // 解法1：暴力
            // 解法2： 字段数， 每个节点（字符）保存下当前 单词 的值，
            // 插入前缀的时候，每个单词都要累加上去；
            // sum的是读到哪个单词就取 该字符 的val

            root = new Node();
        }

        public void insert(String key, int val) {
            // insert，因为要替换掉原来的 key，所以原来的 val也要减掉；，比较下两个差值；
            int diff = val - map.getOrDefault(key, 0);
            map.put(key, val);

            // 接下来开始把这个差值应用到每个字符（节点上）

            Node cur = root;

            char[] chars = key.toCharArray();
            for (char aChar : chars) {
                int idx = aChar - 'a';
                if (cur.nextNodes[idx] == null) {
                    cur.nextNodes[idx] = new Node();
                }

                cur.nextNodes[idx].val += diff;
                cur = cur.nextNodes[idx];
            }
        }

        public int sum(String prefix) {
            char[] chars = prefix.toCharArray();

            Node cur = root;
            for (char aChar : chars) {
                int idx = aChar - 'a';
                if (cur.nextNodes[idx] == null) {
                    // 不存在，直接返回 0 了
                    return 0;
                }

                cur = cur.nextNodes[idx];
            }

            return cur.val;
        }
    }

    public static class Node {
        int val = 0;
        Node[] nextNodes = new Node[26];


    }
}
