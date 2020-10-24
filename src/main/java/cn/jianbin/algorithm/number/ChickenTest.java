package cn.jianbin.algorithm.number;

import lombok.SneakyThrows;

/**
 * @author jianbin
 * @date 2020/8/21 19:14
 */
public class ChickenTest {

    /**
     *   买鸡   百鸡 白钱
     *   鸡翁一值钱五，鸡母一值钱三，鸡雏三值钱一。百钱买百鸡，问鸡翁、鸡母、鸡雏各几何？
     */
    @SneakyThrows
    public static void main(String[] args) {
        hundredChicken();
    }

    public static void hundredChicken() {

        // 肯定小于  20 只老鸡
        for (int a = 0; a < 20; a++) {

            // 100 全部买 老鸡和母鸡 肯定不满足条件   所以 a + b < 100
            for (int b = 0; a + b < 100; b++) {
                int c = 100 - a -b;

                // 因为 3 只小鸡 才值 1 钱   所以  小鸡肯定是 3 的倍数
                if (c % 3 == 0 && (100 - 5 * a - 3 * b) == c / 3) {
                    System.out.println(a + ", " + b + ", " + c);
                }
            }
        }
    }
}
