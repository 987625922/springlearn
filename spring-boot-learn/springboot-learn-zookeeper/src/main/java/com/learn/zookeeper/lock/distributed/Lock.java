package com.learn.zookeeper.lock.distributed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 锁类
 * zookeeper分布式锁
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lock {
    private String lockId;
    private String path;
    private boolean active;

    public Lock(String lockId, String nodePath) {
        this.lockId=lockId;
        this.path=nodePath;
    }
}
