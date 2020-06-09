package org.learn.springdatajpa.dao;

import org.learn.springdatajpa.entity.JpaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends
        JpaRepository<JpaUser, Integer>, JpaSpecificationExecutor<JpaUser> {
    /**
     * 除了调用spring data jpa内置的api，我们也可以在dao接口中定义我们自己的方法，
     * 通过@Query声明jpql或sql语句。
     *
     * @Query
     * value：数据库操作语句
     * nativeQuery：是否是原生查询，默认false，即默认使用jpql查询
     * @Modifying：声明当前是一个更新操作，需要修改数据库数据。
     * 只能用于void或int/Integer的返回类型
     * 因为需要修改数据库数据，未防止修改失败造成未知后果，需要搭配事务管理来是使用
     * @Transactional：添加事务管理支持
     * 一般需要设置rollbackFor或者noRollbackFor，来表示什么情况下进行事务回滚
     * @Rollback：是否可以回滚，默认true
     *
     */
    /**
     * jpql 查询
     *
     * @param userName
     * @param age
     * @return
     */
    @Query(value = "from JpaUser where name = :name and age = :age")
    public JpaUser findUserByName(@Param("name") String userName, @Param("age") int age);

    /**
     * jpql 更新
     *
     * @param id
     * @param userName
     * @return
     */
    @Query(value = "update JpaUser set name = :name where id = :id")
    @Modifying
    public Integer updateNameById(@Param("id") int id, @Param("name") String userName);

    @Query(value = "select * from j_jpa_user where name = :name and age = :age",nativeQuery = true)
    public JpaUser findUserByNameSql(@Param("name") String userName,@Param("age") int age);

}
