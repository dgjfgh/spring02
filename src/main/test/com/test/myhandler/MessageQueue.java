package com.test.myhandler;

/**
 * Created by majianghua on 2018/3/16.
 */
public class MessageQueue {

    Message[] messages=new Message[128];
    int saveIndex =0;
    int findIndex=0;

    /**
     * 加入消息队列
     * @param msg
     */
    public void equeueMessage(Message msg ){
        messages[saveIndex]=msg;
        if (saveIndex==127) {
            saveIndex=0;
        }else {
            saveIndex++;
        }
    }

    /**
     * 获取消息
     * @return
     */
    public Message next(){
        Message msg = messages[findIndex];
        messages[findIndex]=null;
        if (findIndex==127) {
            findIndex=0;
        }else {
            findIndex++;
        }
        return msg;
    }

}
