package cn.jianbin.algorithm.str;

import lombok.SneakyThrows;

import java.util.Scanner;

public class BigNumAddTest {

    @SneakyThrows
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(alwaysAdd(scanner.nextLine(), scanner.nextLine()));
    }

    public static String alwaysAdd(String n1, String n2) {
        if (n1 == null || n1.length() == 0 || n2 == null || n2.length() == 0) {
            return "0";
        }

        boolean s1 = n1.charAt(0) == '-';
        boolean s2 = n2.charAt(0) == '-';
        if (s1) {
            n1 = n1.substring(1);
        }
        if (s2) {
            n2 = n2.substring(1);
        }

        String result = "";
        if (s1 ^ s2) {
            result = subtract(n1, n2);
            if (s1) {
                result = (n1.length() > n2.length() || (n1.length() == n2.length() && n1.compareTo(n2) > 0))
                        ? "-" + result :
                        result;
            }
            if (s2) {
                result =
                        (n1.length() > n2.length() || (n1.length() == n2.length() && n1.compareTo(n2) > 0))
                                ? result :
                                "-" + result;
            }
        } else if (s1) {
            result = "-" + add(n1, n2);
        } else {
            result = add(n1, n2);
        }

        if (result.startsWith("-0")) {
            return "0";
        }

        return result;
    }


    public static String add(String n1, String n2) {
        n1 = new StringBuilder(n1).reverse().toString();
        n2 = new StringBuilder(n2).reverse().toString();


        int max = n1.length();
        int l2 = n2.length();

        if (max < l2) {
            String temp = n2;
            n2 = n1;
            n1 = temp;

            max = n1.length();
            l2 = n2.length();
        }

        int lastStepRemained = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < max; i++) {
            char c = n1.charAt(i);
            int v1 = Integer.parseInt(c + "");
            int v2 = 0;
            if (i < l2) {
                v2 = Integer.parseInt(n2.charAt(i) + "");
            }

            int sum = v1 + v2 + lastStepRemained;
            if (sum >= 10) {
                sum = sum % 10;
                lastStepRemained = 1;
            } else {
                lastStepRemained = 0;
            }
            if (lastStepRemained == 1 && i == max - 1) {
                n1 = n1 + "0";
                max += 1;
            }
            result.append(sum);
        }


        return result.reverse().toString();

    }

    public static String subtract(String n1, String n2) {
        int l1 = n1.length();
        int l2 = n2.length();

        if (l1 < l2) {
            String temp = n2;
            n2 = n1;
            n1 = temp;
        }

        if (l1 == l2) {
            boolean lt = n1.compareTo(n2) < 0;
            if (lt) {
                String temp = n2;
                n2 = n1;
                n1 = temp;
            }
        }

        l1 = n1.length();
        l2 = n2.length();

        n1 = new StringBuilder(n1).reverse().toString();
        n2 = new StringBuilder(n2).reverse().toString();


        int lastRemaind = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l1; i++) {
            int v1 = Integer.parseInt(n1.charAt(i) + "");
            int v2 = 0;
            if (i < l2) {
                v2 = Integer.parseInt(n2.charAt(i) + "");
            }

            int remain = v1 - v2 - lastRemaind;
            if (remain < 0) {
                remain = 10 - v2 + v1 - lastRemaind;
                lastRemaind = 1;
            } else {
                lastRemaind = 0;
            }
            sb.append(remain);
        }

        String r = sb.reverse().toString();
        for (int i = 0; i < r.length(); i++) {
            char c = r.charAt(i);
            if (c != '0') {
                return r.substring(i);
            }
        }

        return "0";
    }
}
