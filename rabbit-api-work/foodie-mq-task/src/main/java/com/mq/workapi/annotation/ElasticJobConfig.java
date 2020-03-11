package com.mq.workapi.annotation;

import com.mq.workapi.autoconfigure.JobParserAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author xulei
 * @date 2020-3-11 17:26
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticJobConfig {

    String name(); //elasticjob的名称

    String cron() default "";

    int shardingTotalCount() default -1;

    String shardingItemParameters() default "";

    String jobParameter() default "";

    boolean failover() default false;

    boolean misfire() default true;

    String description() default "";

    boolean overwrite() default false;

    boolean streamingProcess() default false;

    String scriptCommandLine() default "";

    boolean monitorExecution() default  false;

    int  monitorPort() default -1;  //must

    int  maxTimeDiffSeconds() default -1; //must

    String jobShardingStrategyClass() default ""; //must

    int    reconcileIntervalMinutes() default 10; //must

    String eventTraceRdbDataSource() default ""; //must

    String listener() default "";  //must

    boolean disabled() default false; //must

    String  distributeListenter() default "";

    long startedTimeoutMilliseconds() default  Long.MAX_VALUE;

    long completedTimeoutMilliseconds() default Long.MAX_VALUE;

    String jobExceptionHandler() default "com.dangdang.ddframe.job.executor.handler.impl.DefaultJobExceptionHandler";

    String executorServiceHandler() default "com.dangdang.ddframe.job.executor.handler.impl.DefaultExecutorServiceHandler";
















}
