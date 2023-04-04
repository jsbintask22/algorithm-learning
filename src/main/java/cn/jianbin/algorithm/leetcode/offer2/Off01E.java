package cn.jianbin.algorithm.leetcode.offer2;

/**
 * @author jianbin.
 * @date 2023/4/1 15:30
 *
给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。

 

注意：

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231−1]。本题中，如果除法结果溢出，则返回 231 − 1
 

示例 1：

输入：a = 15, b = 2
输出：7
解释：15/2 = truncate(7.5) = 7
示例 2：

输入：a = 7, b = -3
输出：-2
解释：7/-3 = truncate(-2.33333..) = -2
示例 3：

输入：a = 0, b = 1
输出：0
示例 4：

输入：a = 1, b = 1
输出：1

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/xoh6Oh
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Off01E {
    
    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE - 1);
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(-Integer.MIN_VALUE);


        System.out.println(solution(0, 1));

        System.out.println(solution(-400, 1));

        System.out.println(solution(Integer.MIN_VALUE, 50));

        System.out.println(solution(90, 4));

        System.out.println(solution(90, -4));
    }

    /**
     *  解题入口
     */
    public static int solution(int a, int b) {
        // 被除数位 0， 结果必然为 0 ，直接返回；
        if (a == 0) {
            return 0;
        }


        // 接下来考虑 溢出的可能；  先找出所有可能溢出的 排列，直接返回 结果；
        if (a == Integer.MIN_VALUE) {
            // a = 最小值； b = -1， 此时溢出
            if (b == -1) {
                return Integer.MAX_VALUE;
            }

            // a = 最小值； b = 1，直接返回a
            if (b == 1) {
                return a;
            }
        }

        // 接下来考虑 b 的极值情况；
        if (b == Integer.MIN_VALUE) {
            if (a == Integer.MIN_VALUE) {
                // b = min, a = min,  返回1
                return 1;
            }

            // 其他情况；  b = min， a 不管大于0还是小于0，  都是0（因为除不完）
            return 0;
        }


        // 接下来把 a 和 b 换算成 正数； (也可以全部换算成 负数）
        boolean retFlag = false;

        // 两个数 一正一负，结果则需要变换；

        int ret = 0;
        /*if (a == Integer.MIN_VALUE) {
            // 如果 a 还是为最小值； 为防止溢出，提前 先减一次；
            a = a + Math.abs(b);
            ret++;
        }*/

        if (a > 0) {
            a = -a;
            retFlag = !retFlag;
        }

        if (b > 0) {
            b = -b;
            retFlag = !retFlag;
        }


        // 从这里开始 用 "-" 法代替 除法
        while (a <= b) {
            // a 只要大于 b 就能继续 减
            a = a - b;
            ret++;
        }

        if (retFlag) {
            // 最后结果 取反；
            ret = -ret;
        }


        return ret;
    }
}
