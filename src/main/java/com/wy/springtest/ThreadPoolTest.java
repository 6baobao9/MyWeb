package com.wy.springtest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class ThreadPoolTest implements InitializingBean {

    private static ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 任务阻塞队列，最大任务数10，默认Integer.MAX_VALUE
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue(10);
        // Java线程池类
        // 核心线程数：5，最大线程数：10，任务结束后保留线程时间：10，时间单位：秒
        threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, queue);
        /*
        ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
        ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
        ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     jv   ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
        */
        threadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void add() {
        threadPoolExecutor.submit(new MyDo());
    }

    public class MyDo implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
