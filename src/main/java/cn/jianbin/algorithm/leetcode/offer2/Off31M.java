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
