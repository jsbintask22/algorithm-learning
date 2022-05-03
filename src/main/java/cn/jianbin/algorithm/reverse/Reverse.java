package cn.jianbin.algorithm.reverse;

import cn.jianbin.algorithm.utils.Utils;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author aaron.zou
 * @date 2022/4/9 4:26 下午
 * <p>
 * 暴力递归练习
 */
@UtilityClass
public class Reverse {


    public static void main(String[] args) {
        int n = 4;
        System.out.println("暴力递归汉诺塔问题; n = " + n + ", 步骤: ");
        hanoi(n, 'A', 'C', 'B');


        System.out.println("--------------------------------------------");
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println(stack);

        System.out.println("递归调转栈空间; ");
        reverseStack(stack);
        System.out.println(stack);


        System.out.println("--------------------------------------------");
        String str = "abca";
        List<String> ret = new ArrayList<>();
        strAllSubsequences(str.toCharArray(), 0, ret, "");
        System.out.println(str + " 的所有子序列共: " + ret.size());

        Map<Character, List<String>> collect = ret.stream().collect(Collectors.groupingBy(s -> s.length() == 0 ? ' '
                : s.charAt(0)));

        collect.forEach((k, v) -> {
            System.out.println(k + ": ");
            System.out.println(v);
            System.out.println();
        });


        System.out.println("--------------------------------------------");
        String str2 = "abca";
        List<String> ret2 = new ArrayList<>();
        strFullArrange(str2.toCharArray(), 0, ret2);
        System.out.println(str2 + " 的所有排列组合: " + ret2.size());
        System.out.println(ret2);


        System.out.println("--------------------------------------------");
        String str3 = "11013";
        List<String> ret3 = new ArrayList<>();
        num2Alphabet(str3.toCharArray(), 0, ret3, "");
        System.out.println(str3 + " 所有数字转换可能性: " + ret3.size());
        System.out.println(str3 + " 所有数字转换可能性(动态规划版本): " + dpNum2Alphabet(str3.toCharArray()));
        System.out.println(new LinkedHashSet<>(ret3));


        System.out.println("--------------------------------------------");
        int[] weights = new int[]{2, 2, 1, 4, 5, 2};
        int[] values = new int[]{4, 2, 4, 4, 6, 8};
        int W = 7;
        Utils.printArr(weights);
        Utils.printArr(values);


        System.out.println("重量" + W + "最多能背价值方法1: " + maxPackageValue(weights, values, W, 0, 0));
        System.out.println("重量" + W + "最多能背价值方法2: " + maxPackageValue(weights, values, 0, W));
        System.out.println("重量" + W + "最多能背价值方法3(方法2动态规划版本): " + dpMaxPackageValue(weights, values, 0, W));


        System.out.println("--------------------------------------------");
        int[] pokers = new int[]{1, 2, 10, 1, 2};
        System.out.println("扑克手牌为: ");
        Utils.printArr(pokers);
        System.out.println("先手选择最好结果是: " + first(pokers, 0, pokers.length - 1));
        System.out.println("后手选择最好结果是: " + second(pokers, 0, pokers.length - 1));
        System.out.println("动态规划先后手最好结果: " + dpFirst(pokers));


        System.out.println("--------------------------------------------");
        int N = 5;
        int[] queens = new int[N];
        for (int i = 0; i < N; i++) {
            queens[i] = -1;
        }


        System.out.println(N + " 皇后问题有: " + queenMethods(queens, 0, N) + " 个答案");


        System.out.println("--------------------------------------------");
        int NN = 7;
        int T = 4;
        int CUR = 3;
        int REST = 3;
        int total = 5;
        System.out.println("机器人走路问题： N = " + NN + ", M = " + CUR + ", rest = " + REST + ", T = " + T
                + ", 应该有这么多种走法: " + walk(NN, CUR, T, REST));

        System.out.println("机器人走路问题（动态规划解法）： N = " + NN + ", M = " + CUR + ", rest = " + REST + ", " +
                "T = " + T + ", total = " + total
                + ", 应该有这么多种走法: " + dpWalk0(NN, CUR, REST, total, T));


        System.out.println("--------------------------------------------");
        int[] moneys = new int[]{1, 3, 2};
        int aim = 4;
        Utils.printArr(moneys);
        System.out.println("从数组中找到 " + aim + " 的钱共有: " + moneyAim(moneys, 0, aim, aim));
        System.out.println("从数组中找到 " + aim + " 的钱共有(暴力递归2）: " + moneyAim2(moneys, 0, aim, aim));

    }

    /**
     * 汉诺塔问题；
     * <p>
     * 有三个柱子  A B C
     * <p>
     * 假设现在有 N 个大小不一致按大小从小到大叠加的盘子在 柱子 A 上， 要求按照相同顺序完整移动到 C 或者 B；
     * <p>
     * 求： 每一小步的移动步骤;
     * <p>
     * <p>
     * 解：  要想把 N 个盘子移动到 C；  那么每次应该分为三步；
     * <p>
     * 1. 先把 1 - （n - 1） 的盘子移动到 b
     * 2. 把 n 这个盘子移动到 c
     * 3. 把 1 - （n - 1） 的盘子从 b 移动到 c （把 n -1 想象成 n），重复上面步骤
     */
    public void hanoi(int n, char from, char to, char other) {
        if (n == 1) {
            // base case; 只剩下一个盘子了； 直接移动即可
            System.out.println(String.format("move %s from %s to %s", n, from, to));
            return;
        }

        // 把 1 - （n - 1） 从 from 移动到 other
        hanoi(n - 1, from, other, to);
        // 把 最后一个盘子从 from 移动到 to
        System.out.println(String.format("move %s from %s to %s", n, from, to));
        // 吧 1 - （n -1） 从 other 移动到 from
        hanoi(n - 1, other, to, from);
    }

    /**
     * 怎么不使用额外空间 将一个栈 反转   1 2 3 4   变成  4 3 2 1
     * <p>
     * 在纸上画出递归过程就明白了；  重点在于可以在递归的过程中用一个变量存储东西
     */
    public void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            // base case; 如果栈为空了，该返回了
            return;
        }

        int bottom = popBottom(stack);
        reverseStack(stack);
        stack.push(bottom);
    }


    /**
     * 只弹出 栈的最底部的 值并且返回
     * <p>
     * 1 2 3    =>   1 2
     * <p>
     * return 3
     */
    private int popBottom(Stack<Integer> stack) {
        Integer top = stack.pop();
        // 如果弹出最上方后为空了； 直接返回
        if (stack.isEmpty()) {
            return top;
        }


        int curVal = popBottom(stack);
        stack.push(top);

        return curVal;
    }

    /**
     * 求一个字符串的所有子序列；
     * <p>
     * ps：什么是子串？  什么是子序列？
     * <p>
     * abcd
     * 子串：  所有字符必选顺序连起来；  a ab abc abcd  bc bcd
     * <p>
     * 子序列： 所有字符保证顺序即可；   a ab abc abcd ac ad acd b bc bcd bd c cd d
     *
     * @param chars       所有要用到的字符；   [0..idx) 表示已经用过的字符（选择了 用或者 不用）
     *                    [idx, length) 表示还没用的字符（还没选择)
     * @param sequences   所有答案保存的地方
     * @param curSequence 当前递归过程中的字符串（递归过程中还不完整）
     */
    public void strAllSubsequences(char[] chars, int idx, List<String> sequences, String curSequence) {
        if (idx == chars.length) {
            // 已经到最后一个了（所有字符串都选满了）
            sequences.add(curSequence);
            return;
        }

        // idx 位置选择要
        strAllSubsequences(chars, idx + 1, sequences, curSequence + chars[idx]);

        // idx 位置选择不要
        strAllSubsequences(chars, idx + 1, sequences, curSequence);
    }

    /**
     * 求一个字符串的全排列组合；
     * <p>
     * 如 针对 abcd：有以下排列组合；
     * <p>
     * abcd  abdc  acbd  acdb  adbc  adcb
     * <p>
     * bacd  badc  bcad  bcda  bdca  bdac
     * <p>
     * cbad  cbda  cabd  cadb  cdab  cdba
     * <p>
     * dbca  dbac  dcba  dcab  dacb  dabc
     *
     * @param chars chars[0..idx)  已经确定了的字符；
     *              chars[idx...length) 还没确定的字符
     */
    public void strFullArrange(char[] chars, int idx, List<String> ans) {
        if (idx == chars.length) {
            // base case; 所有字符都确定好了； 直接确定答案，放到 ans中去
            ans.add(String.valueOf(chars));
            return;
        }

        // 还没确定好；
        for (int i = idx; i < chars.length; i++) {
            // 从idx 开始，后面的字符都可以跟  idx 做交换；
            // 假设 idx = 0， i = 1；  那就是 b和a 做交换；
            // 则此时 str = bac，idx = 0，i = 1； 传进下一个递归
            // 则此时 idx = 1；  假设 i = 2， 那就是  ac 做交换；
            // 则此时 str = bca，idx = 1， i = 3，传进下一个递归；
            // 这次是可将 bca 加入答案

            // 将 i 和 idx 交换
            // i 和 idx 位置字符不同时才做交换，去除重复排列
            if (i == idx || chars[i] != chars[idx]) {
                swap(chars, i, idx);

                strFullArrange(chars, idx + 1, ans);
                // 恢复现场
                swap(chars, i, idx);
            }
        }
    }

    private void swap(char[] chars, int i, int j) {
        char c = (char) (chars[i] ^ chars[j]);
        chars[i] = (char) ((chars[i] ^ c));
        chars[j] = (char) (chars[i] ^ c);
    }

    /**
     * 数字字母转换；
     * 假设 1 可以按顺序 转为 A，11 可以转为 AA 或者 L；
     * <p>
     * 求每个数字字符的所有转换可能性
     *
     * @param chars chars[0..idx)  为已经转换好的
     *              chars[idx...length) 剩余待转换的
     */
    public void num2Alphabet(char[] chars, int idx, List<String> ans, String curAlphabet) {
        if (idx == chars.length) {
            // basecase 所有字符都转换好了，直接加入答案
            ans.add(curAlphabet);
            return;
        }

        // 还没有字符没有转换好； 继续
        // 如果单独遇到了 0 ，这条路肯定不可行； 直接返回即可
        if (chars[idx] == '0') {
            return;
        }

        // 接下来 idx 肯定没有遇到 0； 那就是 [1....9]
        // 继续 如果是 3+, 那就没得选了，只能把当前字母直接拼进去
        if (chars[idx] >= '3') {
            num2Alphabet(chars, idx + 1, ans, curAlphabet + convert(chars[idx]));

        } else if (chars[idx] == '1') {
            // 如果是 1 开头，那直接就有两种情况了，  选择 idx 或者 （idx 和 idx + 1） 做转换
            // idx
            num2Alphabet(chars, idx + 1, ans, curAlphabet + convert(chars[idx]));

            // idx 和 idx + 1
            if (idx < chars.length - 1) {
                // 必须 保证 idx + 1 是合法值才行
                num2Alphabet(chars, idx + 2, ans, curAlphabet
                                + convert(
                        (char) (Integer.parseInt(chars[idx] + "" + chars[idx + 1]) + 49)
                        )
                );
            }

        } else {
            // 到这里 只剩下一个 '2' 的可能性了；
            // 2 可以单独做一个可能  或者 2 和 后面一个 小于 6 的合并做一个可能

            // idx单独做可能
            num2Alphabet(chars, idx + 1, ans, curAlphabet + convert(chars[idx]));

            // idx + (idx + idx + 1） 做可能
            if (idx < chars.length - 1 && chars[idx + 1] < '7') {
                // 必须 保证 idx + 1 是合法值才行
                num2Alphabet(chars, idx + 2, ans, curAlphabet
                                + convert(
                        (char) (Integer.parseInt(chars[idx] + "" + chars[idx + 1]) + 49)
                        )
                );
            }
        }
    }

    public char convert(char c) {
        return (char) (c + 16);
    }


    /**
     * 动态规划版本的 字符转换
     */
    public int dpNum2Alphabet(char[] chars) {
        // 假设 有数字   X1 X2 X3 ... Xn  字符串的当前 组合数  f(n)
        // f(n) 的意义表示当前 n 个数字产生的组合数

        // x1 x2 x3 ... x(n-1) 组合数 f(n - 1)
        // x1 x2 x3 ... x(n - 2) 组合数 f(n - 2)

        // x1 x2 x3 ... x(n - 2) x(n - 1) xn   f(n)
        // 将 x(n - 1) xn 看做一个整体求解   则有  f(n) = f(n - 2)
        // 将 xn 单独求解  则有  f(n) = f(n - 1)

        // 综上所述；  当  x(n - 1) 能和 xn 组合翻译时有  [10 - 25] 这些数字

        // 当 n = 1 时，我们知道肯定只有 1 个解；  所以  1 = f(1) = f(0)

        int N = chars.length;

        int[] dp = new int[N + 1];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            // 判断前一个字符是否能跟当前字符组合
            char cur = chars[i - 1];
            char pre = i - 2 >= 0 ? chars[i - 2] : '-';
            if (pre == '0') {
                dp[i] = dp[i - 1];
            } else if (pre == '1') {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else if (pre == '2' && cur < '6') {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                // pre >= 3
                dp[i] = dp[i - 1];
            }
        }

        return dp[N];
    }

    /**
     * 背包问题；
     * <p>
     * 已知 weight[] 和 values[] 分别是重量和价值数组；
     * 比如 weight[i] 和 values[i] 表示 i 号获取的重量与价值
     * <p>
     * 求：给定一个能背重量 W 的背包，求最多能背多少价值的东西
     * <p>
     * m1： 每个货物都有两种选择； 背或者不背； 然后进行下一步
     * <p>
     * m2：
     */
    public int maxPackageValue(int[] weights, int[] values, int W, int curWeight, int curVal) {
        // 如果背包剩余重量不足够 了，到此为止直接返回
        /*if (weights.length == 0) {
            return curVal;
        }*/

        int max = curVal;
        for (int i = 0; i < weights.length; i++) {
            int weight = weights[i];
            // 只要有一件货物重量 低于 剩余空间，就能继续背
            if (weight <= W - curWeight) {
                weights[i] = Integer.MAX_VALUE;
                max = Math.max(max, maxPackageValue(weights, values, W,
                        curWeight + weight,
                        curVal + values[i]));

                // 恢复现场
                weights[i] = weight;
            }
        }

        return max;
    }

    private int[] copy(int[] arr, int exclude) {
        int[] newArr = Arrays.copyOfRange(arr, 0, arr.length);
        for (int i = exclude; i < newArr.length - 1; i++) {
            newArr[i] = newArr[i + 1];
        }

        return Arrays.copyOfRange(newArr, 0, newArr.length - 1);
    }

    /**
     * 同上，递归方法2；  每件货物都可以选择要或者不要
     *
     * @param weights weights[0...idx) 表示已经做过决定了（选了或者没选）
     *                weights[idx...length) 表示还没决定选还是不选
     *                <p>
     *                <p>
     *                <p>
     *                <p>
     *                动态规划解题：
     *                dp[w + 1][v + 1]
     */
    public int maxPackageValue(int[] weights, int[] values, int idx, int rest) {
        if (idx == weights.length) {
            // base case; 都做过决定了，返回当前所代表的价值： 0 （因为没有选）
            return 0;
        }


        // 决定不选择当前货物 idx
        int dontChoose = 0 + maxPackageValue(weights, values, idx + 1, rest);

        // 决定选择当前货物（当前货物还能选择的情况下）
        int choose = -1;
        if (rest >= weights[idx]) {
            choose = values[idx] + maxPackageValue(weights, values, idx + 1, rest - weights[idx]);
        }

        // 返回累计的价值
        return choose == -1 ? dontChoose : Math.max(dontChoose, choose);
    }

    /**
     * 动态规划解题：
     * <p>
     * dp[w + 1][v + 1]
     */
    public int dpMaxPackageValue(int[] weights, int[] values, int idx, int rest) {
        int length = weights.length;
        int[][] dp = new int[length + 1][rest + 1];

        // 初始化。 base case
        //         if (idx == weights.length) return 0;
        // 相当于最后一行 所有值全部初始化了
        for (int i = 0; i < dp[0].length; i++) {
            dp[length][i] = 0;
        }

        // 因为是初始化了最后一行，所以从倒数开始填值
        for (int i = length - 1; i > -1; i--) {
            int[] row = dp[i];
            for (int j = 0; j < row.length; j++) {

                // 如果当前决定不选择 当前货物
                int dontChoose = 0 + dp[i + 1][j];

                // 决定选择
                int choose = -1;

                // 当前剩余容量必须大于 所需重量
                int valid = j - weights[i];
                if (valid >= 0) {
                    choose = values[i] + dp[i + 1][valid];
                }

                dp[i][j] = Math.max(dontChoose, choose);
            }
        }

        Utils.printArr(dp);

        return dp[idx][rest];
    }


    /**
     * 抽纸牌（范围上的 递归 模型）  背包问题是 从左向右 的递归模型
     *
     * 给定一副纸牌；  arr[] 数组； 每个值表示他的分数；
     *
     * 有 玩家 A 和 玩家 B；  每次都是玩家 A 先摸牌（只能摸最左或者最右的牌），玩家 B 再摸牌；
     *
     * 求： 最后获胜玩家的分数
     */


    /**
     * 函数 first（先手的人调用；）
     * 先手的人肯定选择 pokers[l, r] 范围上对自己最有利的情况去选择；
     */
    public int first(int[] pokers, int l, int r) {
        if (l == r) {
            // base case； 如果 l = r，直接拿走就行了（记住这是先手函数）； 另一个人就是0了，对他最不利
            return pokers[r];
        }

        // l ！= r，说明 至少还有还有两张牌待选择；
        // 那我比较我选择 l 还是 r的区别并且返回最有利的作为我的选择；
        // 选择 l 之后，那 + 号后面就变成了后手
        int leftPoint = pokers[l] + second(pokers, l + 1, r);

        // 选择了 r 之后，那 + 号就变成后手
        int rightPoint = pokers[r] + second(pokers, l, r - 1);

        // 比较哪个选择好返回哪个
        return Math.max(leftPoint, rightPoint);
    }

    public String dpFirst(int[] pokers) {
        // 动态规划直接解出 先手、后手的最好选择；
        int N = pokers.length;

        int[][] first = new int[N][N];
        int[][] second = new int[N][N];

        for (int i = 0; i < N; i++) {
            first[i][i] = pokers[i];
            second[i][i] = 0;
        }

        for (int i = 1; i < N; i++) {
            int l = 0;
            int r = i;
            while (l < N && r < N) {
                first[l][r] = Math.max(
                        pokers[l] + second[l + 1][r],
                        pokers[r] + second[l][r - 1]
                );

                second[l][r] = Math.min(
                        0 + first[l + 1][r],
                        0 + first[l][r - 1]
                );

                l++;
                r++;
            }
        }

        Utils.printArr(first);
        System.out.println("----------------second:--------------");
        Utils.printArr(second);

        return "first: " + first[0][N - 1] + ", second: " + second[0][N - 1];
    }

    /**
     * 后手函数； 返回的肯定是在当前情况下最自己最不利的选择（被对手 选择了最好的情况)
     */
    public int second(int[] pokers, int l, int r) {
        if (l == r) {
            // base case; l == r了，说明只有一张牌，因为是后手； 那肯定选不到；  直接返回 0
            return 0;
        }

        // l ！= r，说明我作为后手，肯定至少还能选择一张牌的
        // 假设对手选择了  l，那我能选择 [l - 1, r]，并且选择 [l - 1, r] 这个过程我是作为先手的
        int leftPoint = 0 + first(pokers, l + 1, r);

        int rightPoint = 0 + first(pokers, l, r - 1);

        // 作为后手，对手肯定会给我最差的选择；  所以这里比较最小的返回
        return Math.min(leftPoint, rightPoint);
    }


    /**
     * 八皇后问题： 有n个皇后就有 n * n 个鸽子的棋盘；
     * 要求： 每个皇后在行、列、对角 上不相遇，求 8、9、10 个皇后的摆法
     * <p>
     * 优化：用位运算代替 冲突的查找方式
     *
     * @param queens queens[0...idx) 表示0到idx 已经摆好了皇后了；  如record[1] = 8 表示第2个皇后摆在了第八列
     *               queens[idx...length] 表示还没摆的皇后
     */
    public int queenMethods(int[] queens, int idx, int n) {
        if (idx == n) {
            // base case； 已经全部摆好了，代表了一种解法
            return 1;
        }

        // 还没摆好，遍历当前皇后 idx 在 0...n 列上的所有尝试;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            // 检查摆在 第 idx 行、第 i 列和前面已经摆好的皇后冲不冲突
            if (hasNoConflict(queens, idx, i)) {
                // 不冲突，接着摆下一行
                queens[idx] = i;
                ret += queenMethods(queens, idx + 1, n);
                // 恢复现场
                queens[idx] = -1;
            }
        }

        return ret;
    }

    private boolean hasNoConflict(int[] queens, int idx, int i) {
        for (int cur = 0; cur < idx; cur++) {
            // 跟已经摆好的皇后每一行都不冲突；
            int queen = queens[cur];

            // 列冲突了
            if (queen == i) {
                return false;
            }

            // 对角冲突了
            // 如果两个点差的行 和 差的列相等   说明他们对角了
            if (Math.abs(cur - idx) == Math.abs(i - queen)) {
                return false;
            }
        }

        return true;
    }


    /**
     * 走路问题（斐波那契数列、奶牛）
     * <p>
     * 假设一共有 N 个点 1 - n； 遇到 1 必须往 2 走，遇到 n必须往（n - 1） 走；
     * <p>
     * 在中间的时候 (n / 2) 可以选择往 n / 2 (+ -) 1 位置走;
     * <p>
     * 问：给定 N，当前位置 M，目标位置 T，总共步数 K，有多少种方法从 M 到 T
     */
    public int walk(int N, int M, int T, int K) {


        return walk0(N, M, K, T);
    }

    /**
     * @param N    总共点
     * @param cur  当前位置
     * @param rest 剩余还没走的步数
     * @param T    目标位置
     *             <p>
     *             <p>
     *             经典动态规划： 在walk0 这个方法中，只有 cur、rest 这两个变量； 那我们可以认为这个方法就是
     *             f(cur, rest)；
     *             <p>
     *             假设初始值 为   f(3, 5), N = 7, T = 4
     *             <p>
     *             则 f(3, 5) = f(2, 4) + f(4, 4)
     *             = f(1, 3) + f(3, 3) + f(3, 3) + f(5, 3)
     *             = f(2, 2) + f(4, 2) + f(6, 2) + 2(f(3, 3))
     *             <p>
     *             <p>
     *             所以对于 f(3, 3) 而言，其实如果整个暴力递归去计算， f(3, 3) 是重复计算的；
     *             <p>
     *             那就可以使用一个 二维数组（2个变量） 去缓存或者存储已经计算好的函数的值;
     *             <p>
     *             假设叫 dp[N + 1][K + 1]     N 为共多少个点， K为一共需要走多少步；
     *             <p>
     *             <p>
     *             则可以得到如下二位表格并且赋予其实际意义；
     *             <p>
     *             对于   rest == 0 && cur == T，而言表示找到了一个解；
     *             则 dp[4][0] = 1， cur != T， 则 dp[cur != 4][0] = 0
     *             <p>
     *             对于 cur = 1而言，dp[1][rest] = dp[2][rest - 1]，
     *             假设 rest = 1， 则 dp[2][0] = dp[1][1] = 0
     *             假设 rest = 2， 则 dp[2][1] = d[1][2] ?
     *             <p>
     *             <p>
     *             <p>
     *             对于cur = N（7），dp[7][rest] = dp[6][rest - 1],
     *             假设 rest = 1，则 dp[7][1] = dp[6][0] = 0
     *             假设 rest = 2, 则 ap[7][2] = dp[6][1] = ?
     *             <p>
     *             <p>
     *             而对于其它情况, dp[2-6][rest] = dp[(2-6) - 1][rest - 1] + dp[(2-6) + 1][rest - 1]
     *             假设cur = 2 rest = 1; 则 dp[2][1] = dp[1][0] + dp[3][0] = 0
     *             假设cur = 3，rest = 1； 则dp[3][1] = dp[2][0] + dp[4][0] = 1
     *             <p>
     *             根据以上，可填满 二维表格所有解：
     *             <p>
     *             cur
     *             ||
     *             👇🏻
     *             0   1   2   3   4   5           =》 rest
     *             0   ---------------------
     *             1   0   0   0   1   0   4
     *             2   0   0   1   0   4   0
     *             3   0   1   0   3   0   10
     *             4   1   0   2   0   6   0
     *             5   0   1   0   3   0   10
     *             6   0   0   1   0   4   0
     *             7   0   0   0   1   0   4
     *             <p>
     *             <p>
     *             所以，可以直接定义二维数组，将所有得到的答案加入 缓存 dp[][] 中，返回答案前，先从二维数组中查找
     */
    private int walk0(int N, int cur, int rest, int T) {
        if (rest == 0) {
            // base case; 剩余步数 = 0， 说明全部走完了，检查是不是到目标点了
            return cur == T ? 1 : 0;
        }

        // 还没走完，继续走，
        if (cur == 1) {
            // 当前到了 1 位置，必须往 2 位置走
            return walk0(N, cur + 1, rest - 1, T);
        }

        if (cur == N) {
            // 到了 N 位置，必须往 N - 1 走
            return walk0(N, cur - 1, rest - 1, T);
        }

        // 这里可以选择 往左 或者 往右
        // 往左
        int left = walk0(N, cur - 1, rest - 1, T);

        // 往右
        int right = walk0(N, cur + 1, rest - 1, T);

        return left + right;

    }


    private int dpWalk0(int N, int cur, int rest, int total, int T) {
        int[][] dp = new int[N + 1][total + 1];

        // 初始化
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = i == T ? 1 : 0;
        }


        // 当 rest = 0时，表示剩余步数为0； 这个时候可以放置 dp[cur][0] 的值
        // 必须从 列 开始取值
        int column = dp[0].length;
        for (int c = 0; c < column; c++) {

            for (int r = 1; r < dp.length; r++) {
                if (r == 1 && c > 0) {
                    dp[1][c] = dp[r + 1][c - 1];
                }

                if (r == 7 && c > 0) {
                    dp[7][c] = dp[r - 1][c - 1];
                }

                if (r > 1 & r < 7 & c > 0) {
                    dp[r][c] = dp[r - 1][c - 1] + dp[r + 1][c - 1];
                }

            }
        }

        Utils.printArr(dp);

        //
        return dp[cur][rest];
    }


    /**
     * 找钱问题： 给定一个数组；  数组中的值为 每张纸币的 面额，给定目标   金额  aim，
     * 每张面额都有任意 张；
     * <p>
     * 求： 组合成 aim 面额有多少种方法;
     *
     * @param idx  arr[0...idx) 为已经尝试过了的 面额
     *             arr[idx...length) 为还没有尝试过的纸币
     * @param rest 离目标还剩多少  金额
     */
    public int moneyAim(int[] arr, int idx, int aim, int rest) {
        if (rest < 0) {
            // base case; 剩余金额 小于0；  肯定不可能找到了； 直接返回 0 种结果;
            return 0;
        }

        if (rest == 0) {
            // base case; 没有剩余金额了； 全部填满了； 直接返回一种结果
            return 1;
        }



        // rest > 0； 说明还能继续从 arr 中找符合条件的面额
        int ret = 0;
        for (int i = idx; i < arr.length; i++) {
            int money = arr[i];
            // 当前金额是符合条件的; 继续递归
            ret += moneyAim(arr, i, aim, rest - money);
        }

        return ret;
    }

    public int moneyAim2(int[] arr, int idx, int aim, int rest) {
        if (rest < 0) {
            // base case； 如果剩余要找的金额 = 0； 说明当前情况不符合；直接返回0
            return 0;
        }


        if (idx == arr.length) {
            if ( rest == 0) {
                return 1;
            }

            return 0;
        }

        int ret = 0;
        for (int i = 0; i * arr[idx] <= rest; i++) {
            ret += moneyAim2(arr, idx + 1, aim, rest - (i * arr[idx]));
        }

        return ret;
    }
}