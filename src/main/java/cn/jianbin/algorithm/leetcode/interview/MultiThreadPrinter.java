package cn.jianbin.algorithm.leetcode.interview;

import java.util.concurrent.*;

/**
 * @author jianbin.
 * @date 2023/9/12 22:16
 */
public class MultiThreadPrinter {
    public static void main(String[] args) {
        queue.add(1);
        for (int i = 0; i < 10; i++) {
            executor.submit(new Worker());
        }

        System.out.println("a");
    }

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);
    private static ExecutorService executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.MINUTES, new SynchronousQueue<>());
    private static volatile boolean stoped = false;

    public static class Worker implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    if (stoped) {
                        break;
                    }

                    Integer top = queue.poll(10, TimeUnit.MILLISECONDS);

                    if (top == null) {
                        continue;
                    }

                    // 拿到一个元素；
                    if (top > 100) {
                        break;
                    }

                    if (top == 100) {
                        stoped = true;
                    }

                    // 小于 100，直接打印，然后加入新的元素；
                    System.out.println(top);

                    queue.add(top + 1);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }


            }
        }
    }
}
