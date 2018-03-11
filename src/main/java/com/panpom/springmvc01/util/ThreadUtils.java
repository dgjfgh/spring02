package com.panpom.springmvc01.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步线程控制类
 * <p>
 * 超过上限后需要等待
 *
 * @author sunkai
 * @2014-9-11 上午10:27:43
 */
public class ThreadUtils {
    private final int MAX_SIZE = 25;
    private static ThreadUtils sAsyncExecutor;
    public ExecutorService mExecutor;

    private ThreadUtils() {
        mExecutor = Executors.newFixedThreadPool(MAX_SIZE);
    }

    public static ThreadUtils getInstance() {
        if (sAsyncExecutor == null) {
            synchronized (ThreadUtils.class) {
                if (sAsyncExecutor == null) {
                    sAsyncExecutor = new ThreadUtils();
                }
            }
        }

        return sAsyncExecutor;
    }

    public void execute(Runnable command) {
        if (mExecutor == null) {
            mExecutor = Executors.newFixedThreadPool(MAX_SIZE);
        }
        mExecutor.execute(command);
    }

    public void shutdown() {
        mExecutor.shutdownNow();
        mExecutor = null;
        sAsyncExecutor = null;
    }

    public static void main(String[] args) {
//        ThreadUtils threadUtils = ThreadUtils.getInstance();
//        for (int i = 0; i < 5; i++) {
//            final int finalI = i;
//            threadUtils.execute(new Runnable() {
//                @Override
//                public void run() {
//                    for (int i1 = 0; i1 < 4; i1++) {
//                        System.out.println(finalI + "" + i1 + Thread.currentThread().getName());
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            });
//        }

    }
}
