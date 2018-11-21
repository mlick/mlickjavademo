package com.mlick.javademo.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mlick lixiangxin
 * @create 2018-01-29
 */
public class AddDemo1 {
    private volatile int count = 0;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        AddDemo1 add = new AddDemo1();
        add.doAdd(countDownLatch);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //结果会是 4* 250000 = 100_0000 ?
        System.out.println(add.getCount());
    }

    public void doAdd(CountDownLatch countDownLatch) {
        for (int i = 0; i < 4; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 25_0000; j++) {
                        count++;
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
    }

    public int getCount() {
        return count;
    }


}