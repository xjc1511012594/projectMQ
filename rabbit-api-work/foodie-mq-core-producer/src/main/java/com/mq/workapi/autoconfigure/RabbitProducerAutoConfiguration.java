package com.mq.workapi.autoconfigure;

import com.mq.workapi.annotation.EnableElasticJob;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author xulei
 * @date 2020-3-7 11:47
 */
@EnableElasticJob
@Configuration
@ComponentScan({"com.mq.workapi"})
public class RabbitProducerAutoConfiguration {




}
