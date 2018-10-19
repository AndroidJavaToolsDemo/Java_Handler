package com.xiaxl.demo;

public class LogUtil {

    public static void print(String msg) {
        System.out.println("Thread:" + Thread.currentThread().getName() + "\t" + System.currentTimeMillis() + "\t" + msg);
    }

}
