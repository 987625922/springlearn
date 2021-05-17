package com.learn;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.learn.job.MyDataFlowJob;

/**
 * 把elasticjob注册到zk注册中心
 */
public class ElasticJobRun {

    public static void main(String[] args) {
        // ZK注册中心
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
                new ZookeeperConfiguration("127.0.0.1:2181",
                        "learn-elasticjob")
        );
        regCenter.init();
        /**
         *         ============= 配置simplejob start
         */
        // 定义作业核心配置
//        JobCoreConfiguration coreConfig = JobCoreConfiguration
//                .newBuilder("MySimpleJob", "0/20 * * * * ?", 4)
//                .shardingItemParameters("0=RDP, 1=CORE, 2=SIMS, 3=ECIF").failover(true).build();
//        // 定义SIMPLE类型配置
//        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(
//                coreConfig, MySimpleJob.class.getCanonicalName());
//        // 作业分片策略
//        // 基于平均分配算法的分片策略
//        String jobShardingStrategyClass = AverageAllocationJobShardingStrategy.class.getCanonicalName();
//        // 定义Lite作业根配置
//        // LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).jobShardingStrategyClass(jobShardingStrategyClass).build();
//        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();
//        // 构建Job
//        new JobScheduler(regCenter, simpleJobRootConfig).init();
        /**
         *         ============= 配置simplejob end
         */

        /**
         * ================= 配置 DataflowJob start
         */
        JobCoreConfiguration coreConfig = JobCoreConfiguration
                .newBuilder("MyDataFlowJob", "0/20 * * * * ?", 4)
                .shardingItemParameters("0=RDP, 1=CORE, 2=SIMS, 3=ECIF").failover(true).build();
        DataflowJobConfiguration simpleJobConfig = new DataflowJobConfiguration(
                coreConfig, MyDataFlowJob.class.getCanonicalName(), false);
        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();
        // 构建Job
        new JobScheduler(regCenter, simpleJobRootConfig).init();
        /**
         * ================= 配置 DataflowJob end
         */
    }
}
