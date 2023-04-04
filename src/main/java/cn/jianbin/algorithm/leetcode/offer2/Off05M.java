package cn.jianbin.algorithm.leetcode.offer2;

/**
 * @author jianbin.
 * @date 2023/4/1 19:59
 */
public class Off05M {


    public static void main(String[] args) {
        //System.out.println(solution(new String[]{"abcw","baz","foo","bar","fxyz","abcdef"}));
        System.out.println(solution(new String[]{"eae","ea","aaf","bda","fcf","dc","ac","ce","cefde","dabae"}));
        // ["eae","ea","aaf","bda","fcf","dc","ac","ce","cefde","dabae"]

        System.out.println(solution2(new String[]{"eae","ea","aaf","bda","fcf","dc","ac","ce","cefde","dabae"}));


    }

    public static int solution2(String[] words) {
        // 方案2； 用一个 位数组代替 方案1 中的 r1 和 r2；
        // int[] masks；  mask[i]的 32 位的 低 26 位 表示 words[i] 中 字符 'a' - 'z' 是否已经出现过；
        int n = words.length;
        int[] masks = new int[n];

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char[] chars = word.toCharArray();

            for (int i1 = 0; i1 < chars.length; i1++) {

                // x = 0b1010  # 二进制数 1010，十进制数为 10
                //y = x << 2  # 左移两位，y 的值为 0b101000，十进制数为 40
                //z = x >> 1  # 右移一位，z 的值为 0b0101，十进制数为 5


                // 假如 chars[i1] - 'a' = 5;  表示 chars[i1] = 'f'      101
                // 1010 << 左移 两位  101000
                // 001010
                // 101000


                masks[i] = masks[i] | (1 << ((chars[i1] - 'a')));
            }
        }

        int max = 0;
        for (int i = 0; i < masks.length; i++) {
            for (int j = i + 1; j < masks.length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    // 等于0，说明每一位都没有 重复字符；
                    max = Math.max(words[i].length() * words[j].length(), max);
                }
            }
        }

        return max;
    }

        public static int solution(String[] words) {
        int max = 0;

        // 暴力法，遍历每一种可能，检查最大长度；
        for (int i = 0; i < words.length; i++) {
            char[] oneChars = words[i].toCharArray();
            int[] r1 = new int[256];
            int d1 = 0;

            for (char oneChar : oneChars) {
                r1[oneChar]++;
                d1++;
            }



            for (int j = i + 1; j < words.length; j++) {
                char[] twoChars = words[j].toCharArray();
                int[] r2 = new int[256];

                boolean f2 = true;
                for (char oneChar : twoChars) {
                    if (r1[oneChar] >= 1) {
                        f2 = false;
                        break;
                    }
                }

                if (!f2) {
                    continue;
                }

                int d2 = twoChars.length;

                if (max < d1 * d2) {
                    max = d1 * d2;
                }
            }


        }


        return max;
    }
}
