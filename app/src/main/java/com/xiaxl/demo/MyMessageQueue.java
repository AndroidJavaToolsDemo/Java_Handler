package com.xiaxl.demo;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyMessageQueue {

    // 阻塞队列
    private BlockingQueue<MyMessage> queue;
    private boolean quit = false;

    public MyMessageQueue() {
        // 创建阻塞队列
        queue = new LinkedBlockingQueue<>();
        queue.clear();
    }

    /**
     * 入队
     */
    public boolean enqueueMessage(MyMessage msg) {
        if (msg.target == null) {
            throw new RuntimeException("消息必须有一个消息处理者");
        }
        try {
            queue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    public MyMessage next() {
        MyMessage msg = null;
        if (quit) {
            return null;
        }
        try {
            msg = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return msg;
    }

    //销毁
    public synchronized void quit() {
        quit = true;
    }
}
