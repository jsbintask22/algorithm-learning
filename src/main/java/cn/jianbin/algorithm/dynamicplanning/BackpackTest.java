package cn.jianbin.algorithm.dynamicplanning;

import lombok.SneakyThrows;

public class BackpackTest {
    @SneakyThrows
    public static void main(String[] args) {
        int[] ws = new int[]{4, 3, 1, 2};
        int[] vs = new int[]{3, 2, 2, 3};
        int k = 4;

        int[][] maxValues = maxValues(ws, vs, k);
        for (int i = 0; i < maxValues.length; i++) {
            int[] weights = maxValues[i];
            for (int j = 0; j < weights.length; j++) {
                System.out.print(maxValues[i][j] + "  ");
            }
            System.out.println();
        }


        // 变种： 购物车问题：  有的商品要先买其他商品才行，
        // 1. 所以将可以单独买的进行排序排到前面去
        // 2. 判断条件的时候要判断是否 依赖于其他商品
        // 3. 价值 = 重要度 * 价格
    }


    /**
     * @param weights 每件商品重量
     * @param values  每件商品价值
     * @param k       背包最大容量
     *
     *  时间复杂度：O(n^k)；空间复杂度：O(n^k)
     */
    public static int[][] maxValues(int[] weights, int[] values, int k) {
        int n = weights.length;
        if (n == 0 || n != values.length) {
            throw new IllegalArgumentException("商品参数对应错误");
        }
        if (k == 0) {
            return new int[][]{};
        }

        //  结果
        int[][] mv = new int[n + 1][k + 1];

        // 初始化   默认就是0  去掉也可
        /*for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                if (i == 0 || j == 0) {
                    mv[i][j] = 0;
                }
            }
        }*/

        // 真正的商品 编号从  1 开始   1  -  n
        for (int i = 1; i <= n; i++) {

            // 背包容量从  j = 1 开始， 最大容量  k
            for (int j = 1; j <= k; j++) {
                // 如果当前背包容量 大于  当前商品的重量
                if (j >= weights[i - 1]) {
                    // 当前背包能放最大的价值  =  当前商品价值 + （当前背包容量 - 当前商品重量）后的背包能放的最大价值
                    int currentMax = values[i - 1] + mv[i - 1][j - weights[i - 1]];

                    // 上一个商品放入背包时的 最大价值
                    int previousMax = mv[i - 1][j];

                    // 谁大 用谁
                    mv[i][j] = Math.max(currentMax, previousMax);
                } else {
                    // 如果不行 只能用上一个商品的最大价值
                    mv[i][j] = mv[i - 1][j];
                }
            }
        }

        return mv;
    }
}
