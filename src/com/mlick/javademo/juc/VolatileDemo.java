package com.mlick.javademo.juc;

public class VolatileDemo {


    /**
     * volatile 是对内存可见，禁止指令重排序，但是并不是原子性的，需要synchronized 或者 java.util.concurrent中的原子类
     */
    private static volatile int s = 0;


    private /*synchronized*/ static void increase() {
        s++;
    }

    public static void main(String[] args) {

        Thread[] threads = new Thread[20];

        for (int i = 0; i < 20; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100000; j++) {
                        increase();
                    }

                    System.out.println("子线程==>" + s);
                }
            });

            threads[i].start();
        }

//        int tc;
//        while ((tc = Thread.activeCount()) > 1) {
//            System.out.println("tc=>" + tc);
//            Thread.yield();
//        }
//
//
//        System.out.println(s);
    }


}
