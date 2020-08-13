package cn.jianbin.algorithm.number;

import lombok.SneakyThrows;

import java.util.Scanner;

public class ReverseNumTest {

    @SneakyThrows
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        System.out.println(new StringBuilder(s + "").reverse());
    }

}
