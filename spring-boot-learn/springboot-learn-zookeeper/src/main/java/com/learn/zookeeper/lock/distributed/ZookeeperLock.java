package com.learn.zookeeper.lock.distributed;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 加锁工具类
 * zookeeper分布式锁
 * 写锁
 */
public class ZookeeperLock {
    private String server = "127.0.0.1:2181";
    private ZkClient zkClient;
    private static final String rootPath = "/lock";


    //初始化ZkClient，并创建根节点
    public ZookeeperLock() {
        zkClient = new ZkClient(server, 5000, 20000);
        buildRoot();
    }

    //创建根节点
    public void buildRoot() {
        //如果根节点不存在，就创建
        if (!zkClient.exists(rootPath)) {
            zkClient.createPersistent(rootPath);
            System.out.println("创建根节点成功");
        }
    }

    public Lock lock(String lockId, long timeout) {
        //创建一个临时节点
        Lock lockNode = createLockNode(lockId);
        //尝试去激活锁
        lockNode = tryActiveLock(lockNode);
        //如果没有激活，则等待timeout的时间
        if (!lockNode.isActive()) {
            try {
                synchronized (lockNode) {
                    lockNode.wait(timeout);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //timeout时间内节点还未释放，就报lock timeout错误
        if (!lockNode.isActive()) {
            throw new RuntimeException("lock timeout");
        }
        return lockNode;
    }

    //释放锁
    public void unlock(Lock lock) {
        if (lock.isActive()) {
            zkClient.delete(lock.getPath());
        }
    }

    //尝试激活锁
    private Lock tryActiveLock(Lock lockNode) {
        //获取所有的子节点
        List<String> childList = zkClient.getChildren(rootPath)
                .stream()
                .sorted()
                .map(p -> rootPath + "/" + p)
                .collect(Collectors.toList());
        //获取第一个元素
        String firstNodePath = childList.get(0);
        //如果自己就是第一个节点，就激活锁
        if (firstNodePath.equals(lockNode.getPath())) {
            lockNode.setActive(true);
        } else {
            //否则监听前一个锁
            String upNodePath = childList.get(childList.indexOf(lockNode.getPath()) - 1);
            zkClient.subscribeDataChanges(upNodePath, new IZkDataListener() {
                @Override
                public void handleDataChange(String s, Object o) throws Exception {

                }

                //如果前面一个节点被删除了，再次尝试获取锁
                @Override
                public void handleDataDeleted(String s) throws Exception {
                    System.out.println("节点删除" + s);
                    Lock lock = tryActiveLock(lockNode);
                    synchronized (lockNode) {
                        if (lock.isActive()) {
                            lockNode.notify();
                        }
                    }
                    zkClient.unsubscribeDataChanges(upNodePath, this);
                }
            });
        }
        return lockNode;
    }

    public Lock createLockNode(String lockId) {
        String nodePath = zkClient.createEphemeralSequential(rootPath + "/" + lockId, "lock");
        return new Lock(lockId, nodePath);
    }

}
