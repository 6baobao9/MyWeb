package com.wy.springtest.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 测试Spring异步执行
 * 对需要异步处理的方法添加@Async注释
 */
@Component
public class AsyncTask {
    /**
     * 异步处理无返回值
     */
    @Async
    public void out() throws InterruptedException {
        Thread.sleep(10000);
    }

    /**
     * 异步处理有返回值
     */
    @Async
    public Future<String> outWithResult() throws InterruptedException {
        Thread.sleep(10000);
        return new AsyncResult<>("a");
    }
}
