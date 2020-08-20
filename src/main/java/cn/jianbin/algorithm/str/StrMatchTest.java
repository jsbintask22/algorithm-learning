package cn.jianbin.algorithm.str;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StrMatchTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while ((line = br.readLine()) != null) {
            String s2 = br.readLine();
            System.out.println(test(s2, line));
        }
    }


    /**
     * str:   abcde
     * w:     a*d?
     */
    public static boolean test(String str, String wildcardStr) {
        int si = 0;
        int wi = 0;

        int wild = -1;

        while (si < str.length()) {
            if (wi < wildcardStr.length() &&
                    (str.charAt(si) == wildcardStr.charAt(wi) || wildcardStr.charAt(wi) == '?')) {
                si++;
                wi++;
                wild = -1;

            } else if (wi < wildcardStr.length() && wildcardStr.charAt(wi) == '*') {
                wi++;
                wild = 1;

            } else if (wild != -1) {
                si++;
            } else {
                break;
            }
        }

        return si == str.length() && wi == wildcardStr.length();
    }


    public static boolean help(String s, String p) {
        int sr = 0;
        int pr = 0;
        int st = -1;
        int match = 0;

        while (sr < s.length()) {
            if (pr < p.length() && (s.charAt(sr) == p.charAt(pr) || p.charAt(pr) == '?')) {
                pr++;
                sr++;
            } else if (pr < p.length() && p.charAt(pr) == '*') {
                st = pr;
                match = sr;
                pr++;
            } else if (st != -1) {
                pr = st + 1;
                sr = ++match;
            } else {
                return false;
            }
        }
        while (pr < p.length() && p.charAt(pr) == '*') {
            pr++;
        }
        return pr == p.length();
    }
}