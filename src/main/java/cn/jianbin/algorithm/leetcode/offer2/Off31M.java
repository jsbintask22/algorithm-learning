package cn.jianbin.algorithm.leetcode.offer2;

import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author jianbin.
 * @date 2023/4/8 22:44
 *
 * 剑指 Offer II 031. 最近最少使用缓存
 * 运用所掌握的数据结构，设计和实现一个  LRU (Least Recently Used，最近最少使用) 缓存机制 。
 *
 * 实现 LRUCache 类：
 *
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 *
 * 提示：
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 105
 * 最多调用 2 * 105 次 get 和 put
 *
 *
 * 进阶：是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 *
 *
 * 注意：本题与主站 146 题相同：https://leetcode-cn.com/problems/lru-cache/
 */
@UtilityClass
public class Off31M {

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);

        lruCache.put(2, 1);
        lruCache.put(1, 1);
        lruCache.put(2, 3);
        lruCache.put(4, 4);

        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(2));


        System.out.println(lruCache);
    }

    @Data
    public static class LRUCache {
        /**
         * ["LRUCache","get","put","get","put","put","get","get"]
         * [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
         * <p>
         * [null,-1,null,-1,null,null,2,6]
         */
        private final Map<Integer, Integer> cache = new HashMap<>();
        private final LinkedList<Integer> keys = new LinkedList<>();
        private final int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }

            // 写入最近使用；
            keys.remove((Integer) key);
            keys.addFirst(key);


            return cache.get(key);
        }

        public void put(int key, int value) {
            boolean contains = keys.contains(key);
            if (contains) {
                // 已经包含，更新 最近使用；
                keys.remove((Integer) key);

            } else if (keys.size() == capacity) {
                cache.remove(keys.removeLast());
            }

            cache.put(key, value);
            keys.addFirst(key);
        }
    }
}
