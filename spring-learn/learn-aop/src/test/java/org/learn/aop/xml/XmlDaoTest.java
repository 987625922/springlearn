package org.learn.aop.xml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(locations = {"/application-aop.xml"})
public class XmlDaoTest {

    @Autowired
    private XmlDao dao;

    @Test
    public void test1() {
        dao.save();
    }
}
