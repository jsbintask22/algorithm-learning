package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

/**
 * @author aaron.zou
 * @date 2023/4/20 7:04 PM
 *
 * 剑指 Offer II 073. 狒狒吃香蕉
 * 狒狒喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 *
 * 狒狒可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉，下一个小时才会开始吃另一堆的香蕉。
 *
 * 狒狒喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 * 示例 2：
 *
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 * 示例 3：
 *
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 *
 *
 * 提示：
 *
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 *
 *
 * 注意：本题与主站 875 题相同： https://leetcode-cn.com/problems/koko-eating-bananas/
 */
@UtilityClass
public class Off73M {

    public static void main(String[] args) {
        System.out.println(minEatingSpeed(Utils.arr("30,11,23,4,20"), 5));
    }

    public int minEatingSpeed(int[] piles, int h) {
        // 解法； 二分查找；
        // 警察每 h 小时回来一次，说明 猴子 要在 <=h 的时间内吃完，并且是 刚刚小于 h，假设为 k，
        // k 正好满足 k <= h 的最大一个值， k 代表时间，因此得到的速度是  s，s是满足条件
        // k <= h 所能吃出的最小的值（猴子想要休息）  v（香蕉数量） / h = s
        // 因为查找方法是从 最小或者最大开始 查找，满足单调性，可以使用二分.

        // 怎么写好 二分？

        //  int l = 0,  r = max;
        //  while (l <= r) {
        //      if (c1) l = mid + 1;
        //      else (c2) r = mid - 1;
        //  }

        // 从上面 这个循环出来以后，一定有   l > r(想象一下，只有这种可能）
        // 因此， r 是【最后一个】满足 c1 条件的值， l 是【第一个】满足 c2 条件的值
        // 所以，解题过程就化为了 是要找第一个还是要找最后一个

        // 这个题目；  是要求出一个 满足条件的 最大时间，找出最小 速度；即  verify_time(mid) <= h 的最大值
        // 在想让结果为 l 的情况下，则 l 应该是第一个满足 c2 条件的值，
        // 则 c2: verify_time(mid) <= h  c1: verify_time(mid) > h
        // 这样 l 即为 结果.

        int l = 1;
        int r = 1;
        for (int pile : piles) {
            r = Math.max(r, pile);
        }

        while (l <= r) {
            int mid = l + (r - l >> 1);

            // 计算当前速度需要的时间.
            int hour = 0;
            for (int pile : piles) {
                // pile = 5，mid = 2， 应该得到 3，所以加1
                hour += Math.max((pile + mid - 1) / mid, 1);
            }

            if (hour > h) {
                // c1： cal_time(mid) > h，此时还不满足， 说明 l 是最后一个 【满足】 条件（else）的情况
                // hour > h，说明时间太长了，速度加快.
                l = mid + 1;
            } else {
                // c2:  cal_time(mid) <= h，满足；
                // 否则， hour <= h，说明满足，放慢速度；说明 r 是第一个 【不满足】（if）的情况
                r = mid - 1;
            }
        }

        // 最后结果是 l，第一次满足 hour <= h 的这个值.
        return l;
    }
}
