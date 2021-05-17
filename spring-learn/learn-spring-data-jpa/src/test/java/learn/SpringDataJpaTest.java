package learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.springdatajpa.dao.UserDao;
import org.learn.springdatajpa.entity.JpaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-spring-data-jpa.xml"})
@Slf4j
public class SpringDataJpaTest {

    @Autowired
    private UserDao userDao;

    /**
     * 插入或更新
     * save 传入实体对象有主键则更新，没有则插入
     */
    @Test
    public void textSave() {
        for (int i = 0; i < 10; i++) {
            JpaUser user = new JpaUser();
            user.setName("用户名" + i);
            user.setAge(22);
            JpaUser userResult = userDao.save(user);
            log.info(userResult.toString());
        }
    }

    /**
     * 删除
     */
    @Test
    public void testDelete() {
        userDao.deleteById(2);
    }

    /**
     * 查询
     */
    /**
     * count 统计
     */
    @Test
    public void testConunt() {
        log.info("==> " + userDao.count());
    }

    /**
     * exists 数据库中是否存在
     */
    @Test
    public void testExistsById() {
        boolean b = userDao.existsById(2);
        log.info(b + "");
    }

    /**
     * find 立即加载
     */
    @Test
    public void testFind() {
        Optional<JpaUser> user = userDao.findById(3);
        log.info(user.get().toString());
    }

    /**
     * getOne 延迟加载,返回一个动态代理对象
     */
    @Test
    @Transactional
    public void testGetOne() {
        JpaUser user = userDao.getOne(3);
        log.info(user.toString());
    }

    /**
     * 除了调用spring data jpa内置的api，我们也可以在dao接口中定义我们自己的方法，
     * 通过@Query声明jpql或sql语句。
     */
    @Test
    public void testfindUserByName() {
        JpaUser user = userDao.findUserByName("用户名1", 22);
        log.info(user.toString());
    }

    @Test
    public void testUpdateNameById() {
        userDao.updateNameById(3, "jack1");
    }

    @Test
    public void testFindUserByNameSql() {
        log.info(userDao.findUserByNameSql("用户名3", 22).toString());
    }

    /**
     * jpa制定了一些约定，如果按照这些约定来定义方法名，则会自动解析出sql语句
     */
    @Test
    public void testName() {
        JpaUser user1 = userDao.findByName("tom");
        System.out.println(user1);

        JpaUser user2 = userDao.findByNameLike("t%");
        System.out.println(user2);

        JpaUser user3 = userDao.findByNameLikeAndAge("tom", 18);
        System.out.println(user3);

        List<JpaUser> users = userDao.findByIdBetween(1, 3);
        users.forEach(user -> System.out.println(user));
    }

    /**
     * JpaSpecificationExecutor接口的使用
     *   //查询一个
     *     Optional<T> findOne(@Nullable Specification<T> spec);
     *     //查询全部
     *     List<T> findAll(@Nullable Specification<T> spec);
     *     //查询全部  提供分页功能
     *     Page<T> findAll(@Nullable Specification<T> spec, Pageable pageable);
     *     //查询全部，提供排序功能
     *     List<T> findAll(@Nullable Specification<T> spec, Sort sort);
     *     //统计
     *     long count(@Nullable Specification<T> spec);
     *
     * Specification是对JPA规范中Root、CriteriaQuery、CriteriaBuilder的一层封装，
     * 用于构建过滤条件。实例化Specification需要实现它的toPerdicate方法
     *
     */

    /**
     * 查询表中年龄大于等于18的所有广东人
     */
    @Test
    public void testJpaSpecificationExecutorFindAll() {
        Specification<JpaUser> specification = new Specification<JpaUser>() {
            @Override
            public Predicate toPredicate(Root<JpaUser> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                //分别构造各个单属性的过滤条件
                Predicate namePredicate =
                        criteriaBuilder.like(root.get("address"), "广东%");
                Predicate agePredicate =
                        criteriaBuilder.ge(root.get("age"), 18);//大于等于

                //组合成最终的过滤条件
                Predicate predicate = criteriaBuilder.and(namePredicate,
                        agePredicate);
                return predicate;
            }
        };
        //排序输出
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        //查询
//        List<JpaUser> users =
//                userDao.findAll(specification, sort);
//        users.forEach(user -> System.out.println(user));

        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        //Pageable pageable = PageRequest.of(0,10);//pageIndex，pageSize
        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<JpaUser> users = userDao.findAll(specification, pageable);
        users.forEach(new Consumer<JpaUser>() {
            @Override
            public void accept(JpaUser user) {
                log.info(user.toString());
            }
        });
    }

}
