package cn.jianbin.algorithm.utils;

import cn.hutool.core.util.RandomUtil;
import cn.jianbin.alogthrim.datastructure.BinTreeNode;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class Utils {

    public void printArr(int[] arr) {
        if (arr == null) {
            System.out.println(arr);
            return;
        }




        System.out.println(Arrays.stream(arr).boxed().map(Object::toString).collect(Collectors.joining(", ")));
    }

    public void printArr(int[][] arr) {
        System.out.println();
        System.out.print("  | ");
        for (int i = 0; i < arr[0].length; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();

        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + " | ");
            int column = arr[i].length;
            for (int j = 0; j < column; j++) {
                System.out.print(arr[i][j] + "   ");
            }
            System.out.println();
        }
    }



        public void splitLine() {
        System.out.println("-----------------------------------------------------");
    }

    public void print2DArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            int l = arr[i].length;
            for (int j = 0; j < l; j++) {
                System.out.print(arr[i][j] + "  ");
            }

            System.out.println();
        }
    }

    public int[][] gen2DArr(int i, int j) {
        int[][] matrix = new int[i][j];

        for (int k = 0; k < i; k++) {
            for (int l = 0; l < j; l++) {
                matrix[k][l] = RandomUtil.randomInt(0, 100);
            }
        }

        return matrix;
    }

    public int[] genArr(int l) {
        int[] arr = new int[l];
        for (int i = 0; i < l; i++) {
            arr[i] = RandomUtil.randomInt(0, 100);
        }

        return arr;
    }

    public int[] arr(String nums) {
        return Stream.of(nums.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public int[] arr2(String nums) {
        return Stream.of(nums.split(""))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

    }


    public BinTreeNode genBinTree() {
        BinTreeNode root = new BinTreeNode(1);

        BinTreeNode left = new BinTreeNode(50);
        left.left = new BinTreeNode(60);
        left.right = new BinTreeNode(70);
        left.right.left = new BinTreeNode(80);
        left.right.right = new BinTreeNode(90);


        BinTreeNode right = new BinTreeNode(100);
        right.left = new BinTreeNode(120);

        root.left = left;
        root.right = right;

        return root;
    }
}
