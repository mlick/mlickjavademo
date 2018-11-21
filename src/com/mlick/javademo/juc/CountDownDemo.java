package com.mlick.javademo.juc;

import com.github.kevinsawicki.http.HttpRequest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountDownDemo {

    private static final int SUMS = 1000;

    private int n = 1;

    public static void main(String[] args) {

        new CountDownDemo().startRun();

    }


    private void startRun() {
//
//        String conent = HttpRequest.get("http://www.industryillusion.com").body();
//        System.out.println(conent);


        CountDownLatch cdl = new CountDownLatch(1);

        for (int i = 0; i < SUMS; i++) {
            new MyRunable(cdl).start();
        }

        cdl.countDown();

        System.out.println("end");

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("==" + n);
    }

    public class MyRunable extends Thread {

        private CountDownLatch cdl;

        public MyRunable(CountDownLatch cdl) {
            this.cdl = cdl;
        }

        @Override
        public void run() {

            try {
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            add();
            add_2();

        }

    }


    /**
     * 加入锁机制 重入锁
     */
    private Lock lock = new ReentrantLock();

    private void add() {
        lock.lock();
        try {

            System.out.println("start");
            System.out.println(HttpRequest.get("https://www.lbex.net/").code());

            n++;
            System.out.println("=>" + n);
        } finally {
            lock.unlock();
        }
    }

    private final Object object = new Object();

    private void add_2() {

        synchronized (object) {
            System.out.println("start");
            System.out.println("请求状态码:" + HttpRequest.get("https://www.lbex.net/").code());
            n++;
            System.out.println("=>" + n);
        }

    }

}
