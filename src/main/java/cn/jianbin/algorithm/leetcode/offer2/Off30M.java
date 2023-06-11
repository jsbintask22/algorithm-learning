package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.*;

/**
 * @author jianbin.
 * @date 2023/4/8 21:28
 *
 * 剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：
 *
 * insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
 * remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
 * getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。
 *
 *
 * 示例 :
 *
 * 输入: inputs = ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * 输出: [null, true, false, true, 2, true, false, 2]
 * 解释:
 * RandomizedSet randomSet = new RandomizedSet();  // 初始化一个空的集合
 * randomSet.insert(1); // 向集合中插入 1 ， 返回 true 表示 1 被成功地插入
 *
 * randomSet.remove(2); // 返回 false，表示集合中不存在 2
 *
 * randomSet.insert(2); // 向集合中插入 2 返回 true ，集合现在包含 [1,2]
 *
 * randomSet.getRandom(); // getRandom 应随机返回 1 或 2
 *
 * randomSet.remove(1); // 从集合中移除 1 返回 true 。集合现在包含 [2]
 *
 * randomSet.insert(2); // 2 已在集合中，所以返回 false
 *
 * randomSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
 *
 *
 * 提示：
 *
 * -231 <= val <= 231 - 1
 * 最多进行 2 * 105 次 insert ， remove 和 getRandom 方法调用
 * 当调用 getRandom 方法时，集合中至少有一个元素
 *
 *
 * 注意：本题与主站 380 题相同：https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 *
 */
@UtilityClass
public class Off30M {

    public static void main(String[] args) {

    }

    public static class RandomizedSet {
        private Map<Integer, Integer> map = new HashMap<>();
        private List<Integer> values = new ArrayList<>();
        private Random random = new Random();

        /** Initialize your data structure here. */
        public RandomizedSet() {

        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }

            values.add(val);
            map.put(val, values.size() - 1);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }

            // 从 map中干掉；得到 他在数组中的位置 idx
            Integer idx = map.get(val);

            // 从最后一个 元素 lastVal 替换掉数组中的 idx 位置
            Integer lastVal = values.get(values.size() - 1);
            values.set(idx, lastVal);

            // 更新 lastVal 的索引;
            map.put(lastVal, idx);

            // 关键在于； 要一起加入，一起删掉；
            map.remove(val);
            values.remove(values.size() - 1);


            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return values.get(random.nextInt(values.size()));
        }
    }
}
