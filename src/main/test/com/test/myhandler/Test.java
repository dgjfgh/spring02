package com.test.myhandler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by majianghua on 2018/3/21.
 */
public class Test {
    public static void main(String[] args) {
        Looper.prerpare();


        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println(msg.obj + Thread.currentThread().getName());
            }
        };
        new Thread(new Runnable() {

            public void run() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.obj = "asd";
                        System.out.println("===" + Thread.currentThread().getName());
                        handler.sendMessage(message);
                    }
                }, 1000, 1000);
            }
        }).start();


        Looper.loop();
    }
}
