package com.test.myhandler;

/**
 * Created by majianghua on 2018/3/16.
 */
public class Handler {

    private MessageQueue mQueue;
    private Looper mLooper;

    public Handler() {
        mLooper=Looper.myLooper();
        mQueue=mLooper.mQueue;
    }

    /**
     * 发送消息
     * @param msg
     */
    public void sendMessage(Message msg){
        msg.target=this;//handler
        mQueue.equeueMessage(msg);
    }

    /**
     * 处理消息
     * @param msg
     */
    public void handleMessage(Message msg){

    }

    /**
     * 转发消息
     * @param msg
     */
    public void dispatchMessage(Message msg){
        handleMessage(msg);
    }
}
