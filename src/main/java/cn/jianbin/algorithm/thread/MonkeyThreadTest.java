package cn.jianbin.algorithm.thread;

import lombok.SneakyThrows;

import java.util.Random;

/**
 * @author jianbin
 * @date 2020/9/3 21:07
 */
public class MonkeyThreadTest {
    @SneakyThrows
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Monkey(2, "M___1").start();
            new Monkey(3, "M___2").start();

            System.err.println("----------------------------");
        }

        System.in.read();
    }

    private  static Integer apple = 9;
    private final static Object lock = new Object();

    public static class Monkey extends Thread {
        private long st;
        private int min;

        public Monkey(int min, String name) {
            super(name);
            st = Math.abs(new Random().nextLong() % 10) * 100;
            System.err.println(st);
            this.min = min;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                if (apple >= min) {
                    synchronized (lock) {
                        if (apple >=  min) {
                            apple = apple - min;
                            System.out.println(Thread.currentThread().getName() + " got " + min +
                                    " apples, remained: " + apple);
                            lock.wait(st);
                        }
                    }
                } else {
                    break;
                }
            }
        }
    }
}


