package cn.jianbin.algorithm.thread;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 问题描述：有4个线程和1个公共的字符数组。
 * 线程1的功能就是向数组输出A，
 * 线程2的功能就是向字符输出B，
 * 线程3的功能就是向数组输出C，
 * 线程4的功能就是向数组输出D。
 * 要求按顺序向数组赋值ABCDABCDABCD，ABCD的个数由线程函数1的参数指定。[注：C语言选手可使用WINDOWS SDK库函数]
 */
public class PrintNumTest {
    private volatile int condition = 'A';
    private volatile boolean flag = true;
    private AtomicInteger count;
    List<Thread> threads;
    CountDownLatch downLatch = new CountDownLatch(1);
    StringBuffer sb = new StringBuffer();

    public class Task implements Runnable {
        private int target;

        public Task(int t) {
            this.target = t;
        }

        @Override
        public void run() {
            while (flag) {
                if (target != condition) {
                    LockSupport.park();
                }
                sb.append((char) target);
                unparkOthers();
            }
        }

    }

    public PrintNumTest(int count) {
        this.count = new AtomicInteger(count * 4);
    }


    private void unparkOthers() {
        char c = (char) this.condition;
        if (c == 'A') {
            condition = 'B';
            LockSupport.unpark(threads.get(1));
        } else if (c == 'B') {
            condition = 'C';
            LockSupport.unpark(threads.get(2));
        } else if (c == 'C') {
            condition = 'D';
            LockSupport.unpark(threads.get(3));
        } else if (c == 'D') {
            condition = 'A';
            LockSupport.unpark(threads.get(0));
        }

        if (count.decrementAndGet() == 0) {
            flag = false;
            downLatch.countDown();
        } else {
            LockSupport.park();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        PrintNumTest test = new PrintNumTest(3);
        Thread t1 = new Thread(test.new Task('A'), "t_1");
        Thread t4 = new Thread(test.new Task('D'), "t_4");
        Thread t3 = new Thread(test.new Task('C'), "t_3");
        Thread t2 = new Thread(test.new Task('B'), "t_2");

        ArrayList<Thread> list = new ArrayList<>(4);
        test.threads = list;
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);

        t1.start();
        t4.start();
        t2.start();
        t3.start();

        test.downLatch.await();

        // 最后唤醒他们  防止线程不能正确退出
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        LockSupport.unpark(t3);
        LockSupport.unpark(t4);

        System.out.println(test.sb.toString());
    }
}

