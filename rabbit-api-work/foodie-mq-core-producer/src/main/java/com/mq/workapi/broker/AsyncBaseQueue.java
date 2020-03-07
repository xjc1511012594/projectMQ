package com.mq.workapi.broker;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.*;

/**
 * @author xulei
 * @date 2020-3-7 15:39
 *
 *       使用异步线程池
 */
@Slf4j
public class AsyncBaseQueue {
        //线程数
        private static final int THREAD_SIZE=Runtime.getRuntime().availableProcessors();
        //队列的容量
        private static final int QUEUE_SIEZ=10000;
        //创建线程池
        private static final ExecutorService senderSync = new ThreadPoolExecutor(
                THREAD_SIZE,//线程数
                THREAD_SIZE,//最大线程数
                60L,//空闲线程等待时间
                TimeUnit.SECONDS,//时间单位
                new ArrayBlockingQueue<Runnable>(QUEUE_SIEZ),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("rabbit_client_async_sender");
                        return thread;
                    }
                },//the factory to use when the executor creates a new thread
                new java.util.concurrent.RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                log.error("async sender is error rejected, runnable: {}, executor: {}", r, executor);
            }
        }
        );


        public static void submit(Runnable runnable){
            senderSync.submit(runnable);
        }

}
