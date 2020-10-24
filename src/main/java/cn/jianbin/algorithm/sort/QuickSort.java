package cn.jianbin.algorithm.sort;

import lombok.SneakyThrows;

/**
 * @author jianbin
 * @date 2020/10/23 16:17
 */
public class QuickSort {

    @SneakyThrows
    public static void main(String[] args) {
        int[] arr = new int[]{0, -1, 88, 100, 0, 99, 20};
        quick(arr, 0, arr.length - 1);
        print(arr);


        arr = new int[]{0, -1, 88, 100, 0, 99, 20};
        select(arr);
        print(arr);

        arr = new int[]{0, -1, 88, 100, 0, 99, 20};
        insert(arr);
        print(arr);

        arr = new int[]{0, -1, 88, 100, 0, 99, 20};
        bubble(arr);
        print(arr);


        arr = new int[]{1, 2, 3, 4, 5, 10, -1};
        heap(arr);
        print(arr);


        arr = new int[]{0, -1, 88, 100, 0, 99, 20};
        binaryTree(arr);


    }


    public static void quick(int[] arr, int left, int right) {
        if (left < right) {
            int base = arr[left];
            int i = left;
            int j = right;

            while (i < j) {
                while (i < j && base <= arr[j]) {
                    j--;
                }

                if (i < j) {
                    arr[i] = arr[j];
                    i++;
                }

                while (i < j && base >= arr[i]) {
                    i++;
                }
                if (i < j) {
                    arr[j] = arr[i];
                    j--;
                }
            }
            arr[i] = base;


            quick(arr, left, i - 1);
            quick(arr, i + 1, right);
        }
    }

    public static void select(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int idx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[idx] > arr[j]) {
                    idx = j;
                }
            }

            swap(arr, idx, i);
        }

    }

    public static void insert(int[] arr) {
        for (int i = 0; i < arr.length; i++) {

            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                } else {
                    break;
                }
            }
        }
    }

    public static void bubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j , j + 1);
                }
            }
        }
    }

    public static void heap(int[] arr) {
        int length = arr.length;
        for (int i = arr.length - 1; i > 0; i--) {
            buildMaxHeap(arr, length--);
            swap(arr, 0, length);
        }
    }

    public static void binaryTree(int[] arr) {
        Node root = new Node();
        root.val = arr[0];

        for (int i = 1; i < arr.length; i++) {
            root.addNode(arr[i]);
        }

        printNode(root);
    }

    public static class Node {
        int val;
        Node left;
        Node right;

        void addNode(int o) {
            if (o <= val) {
                if (left == null) {
                    left = new Node();
                    left.val = o;
                } else {
                    left.addNode(o);
                }
            } else {
                if (right == null) {
                    right = new Node();
                    right.val = o;
                } else {
                    right.addNode(o);
                }
            }
        }
    }

    private static void printNode(Node node) {
        if (node.left != null) {
            printNode(node.left);
        }
        System.out.print(node.val + ", ");
        if (node.right != null) {
            printNode(node.right);
        }
    }

    private static void buildMaxHeap(int[] arr, int len) {
        for (int i =len / 2  - 1; i >= 0; i--) {
            heapify(arr, len, i);
        }
    }

    private static void heapify(int[] arr, int len, int i) {
        int left = i * 2 + 1;
        int right = left + 1;
        int largest = i;

        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        if (i != largest) {
            swap(arr, i, largest);
        }
    }


    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }
}
