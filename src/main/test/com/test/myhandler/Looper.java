package com.test.myhandler;

/**
 * Created by majianghua on 2018/3/16.
 */
public class Looper {
    MessageQueue mQueue;
    static ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();

    public Looper() {
        mQueue = new MessageQueue();
    }

    public static void prerpare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one looper can be created Person thread");
        }
        sThreadLocal.set(new Looper());
    }

    /**
     * 获取当前looper
     *
     * @return
     */
    public static Looper myLooper() {
        return sThreadLocal.get();
    }

    /**
     * 轮询消息
     */
    public static void loop() {
        Looper me = myLooper();
        MessageQueue queue = me.mQueue;
        for (; ; ) {
            Message msg = queue.next();
            if (msg == null) {
                continue;
            }

            //转发
            msg.target.dispatchMessage(msg);
        }
    }
}
