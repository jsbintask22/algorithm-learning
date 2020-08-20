package cn.jianbin.algorithm.graph;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * 图的深度优先
 */
public class DfsTest {
    @SneakyThrows
    public static void main(String[] args) {
     //   int[][] maze = getMaze1();
        int[][] maze = getMaze2();


        dfsByBacktracking(maze);

    }

    private static int[][] getMaze2() {
      String s = "0 1 0 0 0\n" +
              "0 1 0 1 0\n" +
              "0 0 0 0 0\n" +
              "0 1 1 1 0\n" +
              "0 0 0 1 0";
      int[][] maze = new int[5][5];
        String[] split = s.split("\n");
        for (int i = 0; i < split.length; i++) {
            String columns = split[i];
            String[] s2 = columns.split("\\s");
            for (int i1 = 0; i1 < s2.length; i1++) {
                maze[i][i1] = Integer.parseInt(s2[i1]);
            }
        }

        return maze;
    }


        private static int[][] getMaze1() {
        Scanner scanner = new Scanner(System.in);

        String mn = scanner.nextLine();
        String[] s = mn.split("\\s");
        int[][] maze = new int[Integer.parseInt(s[0])][Integer.parseInt(s[1])];
        for (int i = 0; i < maze.length; i++) {

            String line = scanner.nextLine();

            String[] data = line.split("\\s");
            for (int j = 0; j < data.length; j++) {
                maze[i][j] = Integer.parseInt(data[j]);
            }
        }
        return maze;
    }

    public static void printMazePath(int[][] maze) {
        int m = maze.length;
        int n = maze[0].length;

        int x = 0, y = 0;
        for (int i = 0; i < m + n; i++) {
            if (maze[x][y] == 0) {
                System.out.println("(" + x + "," + y + ")");
            }
            if (x == m - 1 || maze[x + 1][y] == 1) {
                y++;
                continue;
            }

            if (y == n - 1 || maze[x][y + 1] == 1) {
                x++;
                continue;
            }

            if (x == m - 1 && y == n - 1) {
                break;
            }
        }
    }


    public static class Dot {
        int x;
        int y;
        int direction = 3;
        int count = 1;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int changeDirection() {
            direction = direction % 4 + 1;
            count++;
            return direction;
        }
    }

    public static void dfsByBacktracking(int[][] maze) {
        ArrayList<Dot> path = new ArrayList<>();
        // 构造起点
        int x1 = 0, y1 = 0;
        Dot startDot = new Dot(x1, y1);
        path.add(startDot);


        while (!path.isEmpty()) {
            Dot dot = path.get(path.size() - 1);
            int direction = dot.direction;

            // 表示当前这个节点已经走过了
            maze[dot.x][dot.y] = 2;

            if (dot.x == maze.length - 1 && dot.y == maze[0].length - 1) {
                break;
            }

            int x = dot.x;
            int y = dot.y;

            switch (direction) {
                case 1: {
                    if (dot.x - 1 >= 0 && maze[dot.x - 1][y] == 0) {
                        path.add(new Dot(dot.x - 1, y));
                    }
                }
                case 2: {
                    if (dot.y + 1 < maze[0].length && maze[x][y + 1] == 0) {
                        path.add(new Dot(x, y + 1));
                    }
                }
                case 3: {
                    if (dot.x + 1 < maze.length && maze[x + 1][y] == 0) {
                        path.add(new Dot(x + 1, y));
                    }
                }
                case 4: {
                    if (dot.y - 1 >= 0 && maze[x][y - 1] == 0) {
                        path.add(new Dot(x, y - 1));
                    }
                }
                default: {
                    if (dot.count <= 4) {
                        dot.changeDirection();
                        continue;
                    } else {
                        path.remove(path.size() - 1);
                        maze[x][y] = 0;
                    }
                }
            }
        }

        // 打印
        while (!path.isEmpty()) {
            Dot top = path.remove(0);
            System.out.println("(" + top.x + "," + top.y + ")");
        }
    }
}
