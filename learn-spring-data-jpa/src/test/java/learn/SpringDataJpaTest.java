package learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.springdatajpa.dao.UserDao;
import org.learn.springdatajpa.entity.JpaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

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
        JpaUser user = new JpaUser();
        user.setName("11");
        user.setAge(22);
        JpaUser userResult = userDao.save(user);
        log.info(userResult.toString());
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
        Optional<JpaUser> user = userDao.findById(2);
        log.info(user.get().toString());
    }

    /**
     * getOne 延迟加载,返回一个动态代理对象
     */
    @Test
    public void testGetOne() {
        JpaUser user = userDao.getOne(2);
        log.info(user.toString());
    }
    /**
     * 除了调用spring data jpa内置的api，我们也可以在dao接口中定义我们自己的方法，
     * 通过@Query声明jpql或sql语句。
     */

}
