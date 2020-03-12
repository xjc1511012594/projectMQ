package com.mq.workapi.parser;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.mq.workapi.autoconfigure.JobZookeeperProperties;

/**
 * @author xulei
 * @date 2020-3-12 11:23
 */
public class ElasticJobConfParser {

    private JobZookeeperProperties jobZookeeperProperties;

    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    public JobZookeeperProperties getJobZookeeperProperties() {
        return jobZookeeperProperties;
    }

    public void setJobZookeeperProperties(JobZookeeperProperties jobZookeeperProperties) {
        this.jobZookeeperProperties = jobZookeeperProperties;
    }

    public ZookeeperRegistryCenter getZookeeperRegistryCenter() {
        return zookeeperRegistryCenter;
    }

    public void setZookeeperRegistryCenter(ZookeeperRegistryCenter zookeeperRegistryCenter) {
        this.zookeeperRegistryCenter = zookeeperRegistryCenter;
    }
}
