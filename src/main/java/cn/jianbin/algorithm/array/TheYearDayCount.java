package cn.jianbin.algorithm.array;

import lombok.SneakyThrows;

import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author jianbin
 * @date 2020/8/21 19:30
 * <p>
 * 输入某年某月某日，判断这一天是这一年的第几天？。
 */
public class TheYearDayCount {
    static int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    @SneakyThrows
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            Integer[] date = Stream.of(scanner.nextLine().split("\\s"))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);

            System.out.println(dayCount(date[0], date[1], date[2]));
        }
    }

    public static int dayCount(int y, int m, int d) {
        int count = d;
        for (int i = 0; i < m - 1; i++) {
            // 闰年二月多一天
            if (isLeapYear(y) && i == 3) {
                count += 1 + daysOfMonth[i];
            } else {
                count += daysOfMonth[i];
            }
        }

        return count;
    }

    public static boolean isLeapYear(int y) {
        return (y % 4 == 0 && y % 100 != 0) || y % 400 == 0;
    }


    public static int countContinuousOne(int num) {
        int max = 0;
        while (num != 0) {
            int temp = 0;
            if ((num & 1) == 1) {
                temp++;
            } else {
                temp = 0;
            }
            num >>>= 1;
            max = Math.max(max, temp);
        }

        return max;
    }
}
