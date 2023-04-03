package cn.jianbin.algorithm.array;

import cn.hutool.core.util.ArrayUtil;
import cn.jianbin.algorithm.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class NumSum {

    public static void main(String[] args) {
        int[] arr = ArrayUtil.range(0, 10);
        Utils.printArr(arr);

        Utils.printArr(sum(arr, 5));
    }

    public static int[] sum(int[] arr, int target) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(target - arr[i])) {
                return new int[]{i, map.get(target - arr[i])};
            }

            map.put(arr[i], i);
        }

        return null;
    }
}
