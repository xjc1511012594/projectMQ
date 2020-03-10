package com.mq.workapi.broker;

import lombok.extern.slf4j.Slf4j;
import org.omg.SendingContext.RunTime;

import java.util.concurrent.*;


/**
 * @author xulei
 * @date 2020-3-10 14:59
 */
@Slf4j
public class MessageHolderAsyncQueue {

        //线程数
        private static final int THREAD_SIEZ= Runtime.getRuntime().availableProcessors();
        //队列的容量
        private static final int QUEUE_SIEZ=10000;

        private static final ExecutorService senderAsync=new ThreadPoolExecutor(THREAD_SIEZ,
                THREAD_SIEZ,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(QUEUE_SIEZ),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread();
                        thread.setName("rabbitmq_client_async_sender");
                        return thread;
                    }
                }, new RejectedExecutionHandler() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                    log.error("async sender is error rejected ,runnable {},executor {}",r,executor);
                }// 没有线程空缺时，仍然有任务提交，采取的策略
        });

        public static void submit(Runnable runnable){ senderAsync.submit(runnable);}


}
