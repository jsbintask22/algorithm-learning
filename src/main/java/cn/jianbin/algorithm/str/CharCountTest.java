package cn.jianbin.algorithm.str;

import lombok.SneakyThrows;

import java.util.Scanner;

public class CharCountTest {

    @SneakyThrows
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            System.out.println(firstShow(scanner.nextLine()));
        }
    }

    public static String firstShow(String s) {
        int[] counts = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counts[(int) c]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < counts.length; i++) {
            int count = counts[i];
            if (count == 1) {
                sb.append((char) i);
            }
        }

        String result = sb.toString();
        if (result.length() == 0) {
            return sb.append("-1").toString();
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (result.contains("" + c)) {
                return "" + c;
            }
        }

        return result;
    }
}
