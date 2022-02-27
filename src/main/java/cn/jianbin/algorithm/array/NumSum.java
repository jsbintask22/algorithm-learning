package cn.jianbin.algorithm.array;

import cn.hutool.core.util.ArrayUtil;
import cn.jianbin.algorithm.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author aaron.zou
 * @date 2021/11/6 3:32 下午
 *
 * 数字相加，返回目标值 的下标
 */
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
