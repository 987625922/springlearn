## 对象

1. ### 对象内存布局

   <img src="https://wx2.sbimg.cn/2020/07/29/P0o6o.png" style="zoom:70%;" />

   - #### 对象头

     - ##### 对象运行时数据

       ##### 存储 hashCode、GC 分代年龄、锁类型标记、偏向锁线程 ID 、 CAS 锁指向线程 LockRecord 的指针等， synconized 锁的机制与这个部分( markwork )密切相关，用 markword 中最低的三位代表锁的状态，其中一位是偏向锁位，另外两位是普通锁位。

     - ##### 对象类型指针

       ##### 对象指向它的类元数据的指针、 JVM 就是通过它来确定是哪个 Class 的实例

2. 