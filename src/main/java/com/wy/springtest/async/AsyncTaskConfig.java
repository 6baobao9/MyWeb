package com.wy.springtest.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * 通过实现AsyncConfigurer接口，实现注入自定义Executor作为spring默认线程池
 */

// 注释开启Spring异步处理
@EnableAsync
@Configuration
public class AsyncTaskConfig implements AsyncConfigurer {
    private static Logger logger = LoggerFactory.getLogger(AsyncTaskConfig.class);
    @Value("${com.wy.async.corePoolSize}")
    private int corePoolSize;
    @Value("${com.wy.async.maxPoolSize}")
    private int maxPoolSize;
    @Value("${com.wy.async.queueCapacity}")
    private int queueCapacity;
    @Value("${com.wy.async.threadNamePrefix}")
    private String threadNamePrefix;
    @Value("${com.wy.async.waitForTasksToCompleteOnShutdown}")
    private boolean waitForTasksToCompleteOnShutdown;

    /**
     * 实现接口下getAsyncExecutor方法，返回自定义Executor
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(corePoolSize);//当前线程数
        threadPool.setMaxPoolSize(maxPoolSize);// 最大线程数
        threadPool.setQueueCapacity(queueCapacity);//线程池所使用的缓冲队列
        threadPool.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);//等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setAwaitTerminationSeconds(60 * 15);// 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setThreadNamePrefix(threadNamePrefix);//  线程名称前缀
        threadPool.initialize(); // 初始化
        return threadPool;
    }

    /**
     * 实现接口下getAsyncUncaughtExceptionHandler方法，自定义异常处理
     * 此处对无返回值的异步处理有效，有返回值的利用future.get()捕获异常
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        AsyncUncaughtExceptionHandler handler = new SpringAsyncExceptionHandler();
        return handler;
    }

    class SpringAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            logger.error("Exception occurs in async method", throwable.getMessage());
        }
    }
}
