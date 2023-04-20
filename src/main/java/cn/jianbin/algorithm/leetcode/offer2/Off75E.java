package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author aaron.zou
 * @date 2023/4/20 9:21 PM
 *
 * 剑指 Offer II 075. 数组相对排序
 * 给定两个数组，arr1 和 arr2，
 *
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 *
 *
 * 示例：
 *
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 *
 * 提示：
 *
 * 1 <= arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 *
 * 注意：本题与主站 1122 题相同：https://leetcode-cn.com/problems/relative-sort-array/
 */
@UtilityClass
public class Off75E {

    public static void main(String[] args) {

    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }

        List<Integer> intList = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        intList.sort((v1, v2) -> {
            if (map.containsKey(v1)) {
                return map.get(v1) - map.getOrDefault(v2, Integer.MAX_VALUE);
            }

            // 说明 v1 没被包含在里面.
            if (map.containsKey(v2)) {
                // v2 包含，v2 排在前面
                // 返回正数，说明 v2 比较大；
                return 1;
            }

            // 都不包含，正常排序.
            return v1 - v2;

        });

        for (int i = 0; i < intList.size(); i++) {
            arr1[i] = intList.get(i);
        }

        return arr1;
    }
}
