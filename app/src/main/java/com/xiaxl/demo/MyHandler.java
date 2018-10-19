package com.xiaxl.demo;

public class MyHandler {

    // 用于进行线程间通信的阻塞队列
    private MyMessageQueue queue;
    // 处理消息的回调
    private CallBack callBack;

    /**
     * 构造方法
     *
     * @param callBack
     */
    public MyHandler(CallBack callBack) {
        // 获取looper
        MyLooper looper = MyLooper.myLooper();
        if (looper == null) {
            throw new RuntimeException("在新开的线程中。创建MyHandler对象需要先调用MyLooper.prepare()方法。");
        }
        // 消息队列
        queue = looper.queue;
        // 回调
        this.callBack = callBack;
    }

    //消息接收的回调
    public interface CallBack {
        void handleMessage(MyMessage msg);
    }

    /**
     * 发送消息(消息入队列)
     *
     * @param msg
     */
    public void sendMessage(MyMessage msg) {
        msg.target = this;
        queue.enqueueMessage(msg);
    }

    /**
     * 消息队列中取出消息处理
     */
    public void dispatchMessage(MyMessage msg) {
        callBack.handleMessage(msg);
    }

}