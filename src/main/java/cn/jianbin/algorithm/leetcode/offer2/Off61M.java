package cn.jianbin.algorithm.leetcode.offer2;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author aaron.zou
 * @date 2023/4/15 5:01 PM
 * <p>
 * 剑指 Offer II 061. 和最小的 k 个数对
 * 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * <p>
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * <p>
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * 输出: [1,2],[1,4],[1,6]
 * 解释: 返回序列中的前 3 对数：
 * [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 示例 2:
 * <p>
 * 输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * 输出: [1,1],[1,1]
 * 解释: 返回序列中的前 2 对数：
 * [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 示例 3:
 * <p>
 * 输入: nums1 = [1,2], nums2 = [3], k = 3
 * 输出: [1,3],[2,3]
 * 解释: 也可能序列中所有的数对都被返回:[1,3],[2,3]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums1.length, nums2.length <= 104
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1, nums2 均为升序排列
 * 1 <= k <= 1000
 * <p>
 * <p>
 * 注意：本题与主站 373 题相同：https://leetcode-cn.com/problems/find-k-pairs-with-smallest-sums/
 */
public class Off61M {

    public static void main(String[] args) {

    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 解法； 求值最小的 k 个组合；
        // 先观察。 (0, 0) 肯定是最小的（排序了），然后往后面探索， 可能是 （0，1），（1，0），又或者是 （1，1）
        // 这样一直加下去，加到某个容器里面，检查哪个组合的值是最小的；
        // 所以用 小根堆 来当这个容器， 初始先把 （0，0）放入，然后后面
        // 当前为 （i，j), 则必往容器中添加 （i, j + 1)，
        // 当前为 (i, 0)，则必往容器中添加 (i + 1, 0)

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((n1, n2) -> n1.v1 + n1.v2 - n2.v1 - n2.v2);

        int i = 0;
        int len = nums1.length;
        int len2 = nums2.length;

        while (i < len) {
            int j = 0;

            while (j < len2) {
                priorityQueue.offer(new Node(i, j, nums1[i], nums2[j]));
                j++;
            }

            i++;
        }

        List<List<Integer>> ret = new ArrayList<>();
        while (k-- > 0 && !priorityQueue.isEmpty()) {
            List<Integer> c = new ArrayList<>();
            Node top = priorityQueue.poll();
            c.add(top.v1);
            c.add(top.v2);

            ret.add(c);
        }

        return ret;
    }

    public static class Node {
        int i;
        int j;

        int v1;
        int v2;

        public Node(int i, int j, int v1, int v2) {
            this.i = i;
            this.j = j;
            this.v1 = v1;
            this.v2 = v2;
        }
    }
}
