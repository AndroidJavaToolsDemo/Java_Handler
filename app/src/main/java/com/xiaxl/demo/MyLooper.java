package com.xiaxl.demo;

public class MyLooper {


    // ThreadLocal
    private static ThreadLocal<MyLooper> threadLocal = new ThreadLocal<>();
    private static MyLooper myLooper;

    // 创建消息队列
    public MyMessageQueue queue;

    /**
     * Looper构造方法中创建了消息队列
     */
    private MyLooper() {
        queue = new MyMessageQueue();
    }

    /**
     * 获取当前线程相对应的Looper对象
     *
     * @return
     */
    public static MyLooper myLooper() {
        return threadLocal.get();
    }


    /**
     * 为本线程准备对应的MyLooper对象
     */
    public static void prepare() {
        if (threadLocal.get() != null) {
            throw new RuntimeException("Only one MyLooper may be created per thread");
        }
        // 创建looper
        threadLocal.set(new MyLooper());
    }

    /**
     * 启动消息循环
     */
    public static void loop() {
        //
        while (true) {
            // 获取当前显示looper
            myLooper = myLooper();
            // 获取消息队列
            MyMessageQueue mQueue = myLooper.queue;
            // take()方法是个阻塞方法。线程运行到此会阻塞住。以准备接收发过来的消息
            MyMessage msg = mQueue.next();
            msg.target.dispatchMessage(msg);
        }
    }
}