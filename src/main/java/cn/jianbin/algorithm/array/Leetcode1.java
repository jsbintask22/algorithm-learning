package cn.jianbin.algorithm.array;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jianbin
 * @date 2020/10/20 19:38
 */
public class Leetcode1 {
    @SneakyThrows
    public static void main(String[] args) {

    }


    /**
     *  给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     */
    public int[] add(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            if (map.containsKey(target - v)) {
                return new int[]{map.get(map.get(target - v)), i};
            }

            map.put(v, i);
        }

        throw new RuntimeException("xxx");
    }
}
