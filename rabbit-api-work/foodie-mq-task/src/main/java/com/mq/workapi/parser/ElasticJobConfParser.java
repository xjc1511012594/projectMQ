package com.mq.workapi.parser;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.mq.workapi.annotation.ElasticJobConfig;
import com.mq.workapi.autoconfigure.JobZookeeperProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Iterator;
import java.util.Map;

/**
 * @author xulei
 * @date 2020-3-12 11:23
 */
public class ElasticJobConfParser implements ApplicationListener<ApplicationReadyEvent> {

    private JobZookeeperProperties jobZookeeperProperties;

    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    public ElasticJobConfParser(JobZookeeperProperties jobZookeeperProperties, ZookeeperRegistryCenter zookeeperRegistryCenter) {
        this.jobZookeeperProperties = jobZookeeperProperties;
        this.zookeeperRegistryCenter = zookeeperRegistryCenter;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //对注解进行解析
        ApplicationContext context = event.getApplicationContext();
        Map<String, Object> annotationMap = context.getBeansWithAnnotation(ElasticJobConfig.class);
        for(Iterator<Object> it = annotationMap.values().iterator();it.hasNext();){
            Object confBean = it.next();
        }


    }
}
