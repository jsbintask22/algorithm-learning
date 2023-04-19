package cn.jianbin.algorithm.leetcode.offer2;

import lombok.experimental.UtilityClass;

/**
 * @author jianbin.
 * @date 2023/4/17 21:10
 *
 * 剑指 Offer II 069. 山峰数组的顶部
 * 符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：
 *
 * arr.length >= 3
 * 存在 i（0 < i < arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [0,1,0]
 * 输出：1
 * 示例 2：
 *
 * 输入：arr = [1,3,5,4,2]
 * 输出：2
 * 示例 3：
 *
 * 输入：arr = [0,10,5,2]
 * 输出：1
 * 示例 4：
 *
 * 输入：arr = [3,4,5,1]
 * 输出：2
 * 示例 5：
 *
 * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
 * 输出：2
 *
 *
 * 提示：
 *
 * 3 <= arr.length <= 104
 * 0 <= arr[i] <= 106
 * 题目数据保证 arr 是一个山脉数组
 *
 *
 * 进阶：很容易想到时间复杂度 O(n) 的解决方案，你可以设计一个 O(log(n)) 的解决方案吗？
 *
 *
 *
 * 注意：本题与主站 852 题相同：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/
 */
@UtilityClass
public class Off70M {

    public static void main(String[] args) {

    }

    public int solution2(int[] arr) {
        // 解法2； 已知 数组中只有一个 这样的 值，
        // 假设这个下标 是  x，则  x一定是 偶数（因为是排序的
        // 在 x 的左边 y（偶数）肯定有 arr[y] = arr[y + 1]，
        // 在 x 的右边 z （偶数）肯定有 arr[z] != arr[z + 1],
        // 因为在 二分的过程中， 只要确保了 mid 是偶数 并且满足了
        // arr[mid] = arr[mid + 1]，就把 left 往右边逼近
        // 不满足则 把 right 往左边逼近

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left >> 1);
            // 如果mid 是偶数， 则 &1 后为 0，-0 后偶数不变.
            // 如果 mid 是奇数，则 &1 后为 1， -1 后就变成了 偶数
            mid -= mid & 1;

            // 1 1 2 5 5 6 6 9 9
            if (arr[mid] == arr[mid + 1]) {
                // 满足了 在 x 的左边，mid又是偶数，可以直接 +2，肯定会落在一个偶数处
                left += mid + 2;
            } else {
                // 不满足，说明 mid 在x 右边 OR = x
                right = mid;
            }
        }

        return left;
    }

    public int peakIndexInMountainArray(int[] arr) {
        // 解法； 二分；
        // 根据题意， 假设   k 满足山峰。
        // 假如  i < k
        // 则肯定有  arr[i] < arr[i + 1],
        // 加入 i > k
        // 则肯定有  arr[i] > arr[i + 1];
        // 利用二分不断逼近这个值.

        int left = 0;
        int right = arr.length - 2;
        int ret = -1;
        while (left <= right) {
            int mid = left + (right - left >> 1);

            if (arr[mid] > arr[mid + 1]) {
                ret = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ret;
    }
}
