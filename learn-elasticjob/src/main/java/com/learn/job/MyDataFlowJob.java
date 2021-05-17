package com.learn.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Dataflow 类型用于处理数据流，
 * 必须实现 fetchData()和processData()的方法，
 * 一个用来获取数据，一个用来处理获取到的数据。
 */
@Slf4j
@Component
public class MyDataFlowJob implements DataflowJob<String> {
    private boolean flag = false;

    public List<String> fetchData(ShardingContext shardingContext) {
        log.info("开始获取数据");
        if (flag) {
            return null;
        }
        return Arrays.asList("qingshan", "jack", "seven");
    }

    public void processData(ShardingContext shardingContext, List<String> data) {
        for (String val : data) {
            // 处理完数据要移除掉，不然就会一直跑,处理可以在上面的方法里执行。这里采用 flag
            log.info("开始处理数据：" + val);
        }
        flag = true;
    }
}
