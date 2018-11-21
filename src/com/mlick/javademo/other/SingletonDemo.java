package com.mlick.javademo.other;

public class SingletonDemo {


    /**
     * 懒汉模式的 单例模式  线程安全 双重校验锁
     */
    private static volatile SingletonDemo singleton = null;

    public static SingletonDemo getInstance() {

        if (singleton == null) {

            synchronized (SingletonDemo.class) {
                if (singleton == null) {
                    singleton = new SingletonDemo();
                }
            }
        }

        return singleton;

    }

    public static String getTestStr() {

        return "1";
    }


    public static void main(String[] args) {



    }

}
