package cn.jianbin.algorithm.exam;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


public class Main2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            test(scanner.nextLine());
        }
    }

    public static void test(String str) {
        if (str == null || str.length() == 0) return;

        char[] chars = str.toCharArray();
        int[] counts = new int[156];

        // count -> char   a: 2  b:1   c:3
        TreeMap<Character, Integer> countMap = new TreeMap<>();

        // 最多出现的次数
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

           /* if (countMap.containsKey(c)) {
                countMap.put(c, countMap.get(c) + 1);
            } else {
                countMap.put(c, 1);
            }

            if (countMap.get(c) > max) {
                max = countMap.get(c);
            }*/

            counts[c] = counts[c] + 1;
            if (counts[c] > max) {
                max = counts[c];
            }
        }

       /* while (max >0) {

            if (countMap.containsValue(max)) {
                countMap.forEach((key, value) -> {
                    if (counts[value] != 1) {

                        if (value >= 'A' && value <= 'Z') {
                            char n = (char) (value + (int) ('a' - 'A'));

                            // 包含一个 小写的还没打印  并且 他们次数相等
                            int newCount = getCount(n, countMap);
                            if (countMap.containsValue(n) && newCount == key) {
                                if (counts[n] == 0) {
                                    System.out.println(n + ":" + newCount + ";");
                                    counts[n] = 1;
                                }
                            }

                            System.out.println(value + ":" + key + ";");
                        } else {
                            System.out.println(value + ":" + key + ";");
                        }
                    }
                });
            }

            max--;
        }*/


        int[] temp = new int[156];
        while (max > 0) {
            for (int i = 0; i < counts.length; i++) {
                int count = counts[i];
                char c = (char) i;

                // 先输出最大的
                if (count == max) {
                    if (c >= 'a' && c <= 'z' && temp[c] != 1) {
                    } else {
                        // 如果 它 是大写    找到它的小写
                        char k = (char ) (i + 'a' - 'A');

                        // 小写次数和 大写 一样  先打印小写
                        if (counts[k] == count && temp[k] != 1) {
                            System.out.print(k + ":" + counts[k] + ";");
                            temp[k] = 1;
                        }
                    }

                    if (temp[c] != 1) {
                        temp[c] = 1;
                        System.out.print(c + ":" + counts[c] + ";");
                    }


                }
            }

            max--;
        }

    }

    public static int getCount(char c, Map<Integer, Character> countMap) {
        ValueHolder count = new ValueHolder(0);
        countMap.forEach((key, value) -> {
            if (c == value) {
                count.i = key + 1;
            }
        });

        return count.i;
    }

    public static class ValueHolder {
        int i = 0;

        public ValueHolder(int i) {
            this.i = i;
        }

        public void set(int i) {
            this.i = i;
        }
    }
}
