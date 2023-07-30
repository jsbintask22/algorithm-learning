package cn.jianbin.algorithm.leetcode.top100;

import cn.jianbin.algorithm.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jianbin.
 * @date 2023/7/29 13:13
 */
public class T15M {

    public static void main(String[] args) {
        int[] arr = Utils.arr("-1,0,1,2,-1,-4");

        System.out.println(threeSum(arr));
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        // 设 3 个指针； i, k, j
        // 给数组排序（结果只要元素内容并且不用原先顺序）
        // 指针 i 从 0 - len 开始便利
        // 指针 k = i + 1 从 i - len 开始遍历
        // 指针 j 每次从最后开始便利；

        Arrays.sort(nums);

        int len = nums.length;

        int i = 0;

        // 三个指针，确保后面两个指针有位置；
        List<List<Integer>> ret = new ArrayList();
        // -4, -1, -1, 0, 1, 2
        while (i < len - 2) {
            int n1 = nums[i];

            if (n1 > 0) {
                break;
            }

            if (i > 0 && n1 == nums[i - 1]) {
                i++;
                continue;
            }

            int k = i + 1;
            int j = len - 1;

            while (k < j) {
                // 只要 k 和 j 后面两个指针还没相遇； 就要比较；
                int sum = n1 + nums[k] + nums[j];

                if (sum == 0) {
                    List<Integer> r1 = new ArrayList();
                    r1.add(n1);
                    // 加完之后，k 和 j 必须两个同事移动（因为只移动一个结果肯定不等于 0 了）
                    r1.add(nums[k]);
                    r1.add(nums[j]);

                    ret.add(r1);

                    // = 0，把当前结果加到结果集合； 然后思考下一步 k + 1 还是 j - 1；
                    // 假如 k = k + 1, 则要继续往前面移动并且摒弃这个结果（重复了）
                    while (k < j && nums[k] == nums[++k]) {

                    }

                    while (k < j && nums[j] == nums[--j]) {
                    }
                } else if (sum < 0) {
                    // sum < 0，说明 k 还不够大；
                    k++;
                } else {
                    // sum > 0, 说明 j 还不够小；
                    j--;
                }
            }

            i++;
        }


        return ret;
    }


}
