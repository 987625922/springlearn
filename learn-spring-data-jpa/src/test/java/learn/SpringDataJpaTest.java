package learn;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.learn.springdatajpa.dao.UserDao;
import org.learn.springdatajpa.entity.JpaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/application-spring-data-jpa.xml"})
@Slf4j
public class SpringDataJpaTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void textSave(){
        JpaUser user = new JpaUser();
        user.setName("11");
        user.setAge(22);
        JpaUser userResult = userDao.save(user);
        log.info(userResult.toString());
    }
}
