package cn.jianbin.algorithm.random;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class RandomProbability {

    public static void main(String[] args) {
        // 1. 有一个数组； arr； 快速球出去数组上 arr[l, r] 的和
        // 1.1 使用二维数组； 将每次相加的和存起来，下次请求可以直接拿值；   数组的大小是原先的数组的 平方
        // 1.2 使用前缀数组， 另起一个一维数组，将原数组的前每个元素相加

        int[] arr = Utils.genArr(10);
        Utils.printArr(arr);
        int length = arr.length;

        int[][] matrix = new int[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i == j) {
                    matrix[i][j] = arr[i];
                } else if (i > j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = matrix[i][j - 1] + arr[j];
                }
            }
        }

        Utils.print2DArr(matrix);

        System.out.println(" 3 - 5 sum: " + matrix[3][5]);

        int[] preSum = new int[length];
        for (int i = 0; i < length; i++) {
            preSum[i] = i == 0? arr[0] : preSum[i - 1] + arr[i];
        }

        System.out.println(" 3 - 5 sum: " + (preSum[5] - preSum[2]));


        // 2. Math.random() 表示 [0, 1) 上的随机数，并且每个都是等概率的；
        // 2.1  这样 Math.random() * K 则表示  【0， K）上的数
        // 2.2  如果想让 一个数 X 的概率变成 X^2 ，则用 Max(Math.random(), Math.random())

        int k = 1;
        int count = 100_0000;
        double target = 0.99 * k;
        int tartCount = 0;
        for (int i = 0; i < count; i++) {
            if ((Math.max(Math.random() * k, Math.random() * k) < target)) {
                tartCount++;
            }
        }

        System.out.println(target + " probability: " + (double) tartCount / count);


        int[] counts = new int[100];
        for (int i = 0; i < 1000_0000; i++) {
            counts[f5()]++;
        }

        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " : " + counts[i]);
        }

        System.out.println("-------------------------------------------------------------------------");
        for (int i = 0; i < 1000_0000; i++) {
            counts[tenOrTwenty()]++;
        }

        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " : " + counts[i]);
            counts[i] = 0;
        }


        System.out.println("-------------------------------------------------------------------------");
        for (int i = 0; i < 1000_0000; i++) {
            counts[equalsProbability()]++;
        }

        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " : " + counts[i]);
            counts[i] = 0;
        }


    }

    public static int f1() {
        return ((int) (Math.random() * (37 - 17 + 1)) + 17);
    }

    public static int zeroOrOne() {
        int ans;
        do {
            ans = f1();
        } while (ans == 27);

        return ans > 27 ? 1 : 0;
    }

    public static int f3() {
        return (zeroOrOne() << 4)
                +
                (zeroOrOne() << 3)
                +
                (zeroOrOne() << 2)
                +
                (zeroOrOne() << 1)
                +
                zeroOrOne();
    }

    public static int f4() {
        int ans;
        do {
            ans = f3();
        } while (ans > 18);

        return ans;
    }

    public static int f5() {
        return f4() + 49;
    }

    public int tenOrTwenty() {
        return Math.random() < 0.84 ? 10 : 20;
    }

    public int equalsProbability() {
        int ans;
        do {
            ans = tenOrTwenty();
        } while (ans == tenOrTwenty());

        return ans;
    }


}
