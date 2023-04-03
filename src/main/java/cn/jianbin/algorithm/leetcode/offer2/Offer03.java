package cn.jianbin.algorithm.leetcode.offer2;

import cn.jianbin.algorithm.utils.Utils;

/**
 * @author jianbin.
 * @date 2023/4/1 18:25
 *
 *
 * 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
 * 给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
 *
 *
 *
 * 示例 1:
 *
 * 输入: n = 2
 * 输出: [0,1,1]
 * 解释:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 示例 2:
 *
 * 输入: n = 5
 * 输出: [0,1,1,2,1,2]
 * 解释:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 *
 *
 *
 * 说明 :
 *
 * 0 <= n <= 105
 *
 *
 * 进阶:
 *
 * 给出时间复杂度为 O(n*sizeof(integer)) 的解答非常容易。但你可以在线性时间 O(n) 内用一趟扫描做到吗？
 * 要求算法的空间复杂度为 O(n) 。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount ）来执行此操作。
 */
public class Offer03 {

    public static void main(String[] args) {
        Utils.printArr(solution(100));

        Utils.printArr(solution2(100));

    }

    public static int[] solution(int a) {
        int[] ret = new int[a + 1];

        for (int i = 0; i <= a; i++) {
            ret[i] = countOne(i);
        }

        return ret;
    }

    /**
     */
    public static int[] solution2(int a) {
        // 解法2； 根据规律；
        // 000   0
        // 001   1
        // 010   2
        // 011   3
        // 100   4
        // 当 a = 奇数时，肯定等于 (a - 1) 的1 的个数 + 1（因为要在最后面 + 1），   ret[i] = ret[i - 1] + 1
        // 当 a = 偶数时，肯定等于 a / 2 的 1 的个数， 因为等于偶数，表示 最后一位肯定是 0， 除以 2 表示往右移 1 为，抹除了一个 0 而已，1 的个数不变；  ret[i] = ret[i/2]

        int[] ret = new int[a + 1];
        for (int i = 0; i <= a; i++) {
            // 判断 奇数 偶数 用 %
            if (i % 2 == 0) {
                ret[i] = ret[i / 2];
            } else {
                ret[i] = ret[i - 1] + 1;
            }
        }

        return ret;

    }

    public static int countOne(int a) {
        // 根据二进制规律；  没加1 都是在原有的二进制基础上 + 1；  如果 用 a & (a - 1) 可以把新加 1 抹除掉； 以此来统计1 的个数；
        // 1000 8
        // 0111 7
        // 0110 6

        int ret = 0;

        while (a > 0) {
            a &= a - 1;
            ret++;
        }

        return ret;
    }
}
