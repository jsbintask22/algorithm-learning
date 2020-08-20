package cn.jianbin.algorithm.number;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author jianbin
 * @date 2020/8/19 18:46
 */
public class ZiShouTest {

    @SneakyThrows
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = br.readLine()) != null) {

            int val = Integer.parseInt(str);
            int count = 0;
            for (int i = 0; i < val; i++) {
                int l = (i + "").length() + 1;
                int k = 1;
                while (l != 0) {
                    k = k * 10;
                    l--;
                }

                if ((i * i - i) % k == 0) {
                    count++;
                    System.out.print(i + " ");
                }
            }
            System.err.println(count);

        }
    }
}
