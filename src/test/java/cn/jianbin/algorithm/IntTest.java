package cn.jianbin.algorithm;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.TreeSet;

public class IntTest {

    @Test
    public void test1() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String s = scanner.next();
            System.err.println(Integer.parseInt(s.replace("0x", ""), 16));
        }
    }

    @Test
    public void test2() {
        getAllPrimeNums(180);
    }

    @Test
    public void test3() {
        getReverseSetNum("9714121");


        System.out.println();
        System.out.println("我abc1叇".length());
        System.out.println("我abc1叇。".length());
        System.out.println("我abc1叇。".getBytes().length);



        System.out.println("囕囖".toCharArray().length);
        System.out.println("囕囖".getBytes(StandardCharsets.UTF_8).length);
    }

    @Test
    public void test4() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(scanner.nextLine());
        }
    }


    /**
     * 求所有的质因子
     *
     * @param num
     */
    public static void getAllPrimeNums(int num) {
        for (int i = 2; i <= num; i++) {
            if (num % i == 0) {
                System.out.print(i + " ");

                getAllPrimeNums(num / i);
            }

            if (i == num) {
                System.out.print(i + " ");
            }

        }
    }


    public static void getReverseSetNum(String num) {
        char[] chars = num.toCharArray();
        TreeSet<String> treeSet = new TreeSet<>((v1, v2) -> {
            if (v1.equals(v2)) {
                return 0;
            }
            return -1;
        });
        for (int i = 0; i < chars.length; i++) {
            treeSet.add(chars[i] + "");
        }

        treeSet.forEach(System.out::print);
    }
}
