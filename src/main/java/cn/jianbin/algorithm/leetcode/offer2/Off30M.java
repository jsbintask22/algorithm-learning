package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.*;

/**
 * @author jianbin.
 * @date 2023/4/8 21:28
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

            Integer idx = map.remove(val);
            Integer lastVal = values.get(values.size() - 1);
            values.set(idx, lastVal);
            values.remove(val);
            map.put(lastVal, idx);

            values.remove(values.size() - 1);

            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return values.get(random.nextInt(values.size()));
        }
    }
}
