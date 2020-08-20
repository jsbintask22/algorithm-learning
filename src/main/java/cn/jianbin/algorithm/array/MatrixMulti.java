package cn.jianbin.algorithm.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 矩阵乘法的运算量与矩阵乘法的顺序强相关。
 *
 *
 * 例如：
 *
 *     A是一个50×10的矩阵，B是10×20的矩阵，C是20×5的矩阵
 *
 *
 *
 * 计算A*B*C有两种顺序：（（AB）C）或者（A（BC）），前者需要计算15000次乘法，后者只需要3500次。
 *
 *
 *
 * 编写程序计算不同的计算顺序需要进行的乘法次数
 */
public class MatrixMulti {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // Scanner sc = new Scanner(System.in);
        String str = null;
        while ((str = br.readLine()) != null) {
            int num = Integer.parseInt(str);
            int [][] arr = new int[num][2];

            for(int i = 0;i<num;i++) {
                String [] sa = br.readLine().split(" ");
                arr[i][0] = Integer.parseInt(sa[0]);
                arr[i][1] = Integer.parseInt(sa[1]);
            }

            int n = arr.length -1;
            char [] ca = br.readLine(). toCharArray();
            Stack<Integer> stack = new Stack<>();
            int sum = 0;
            for(int i = ca.length - 1;i>=0;i--) {
                char one = ca[i];
                if(one == ')') {
                    stack.push(-1);
                }else if(one == '(') {
                    int n1 = stack.pop();
                    int n2 = stack.pop();
                    sum+= arr[n1][0]*arr[n2][0]*arr[n2][1];
                    arr[n1][1] = arr[n2][1];
                    stack.pop();
                    stack.push(n1);
                }else {
                    stack.push(n);
                    n--;
                }
            }
            System.out.println(sum);
        }
    }

}