package com.wind.spring.databases;

import com.wind.spring.databases.hibernate.bean.HBook;
import com.wind.spring.databases.hibernate.service.HBookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring/application-hibernate.xml"})
public class HibernateTest {


    @Autowired
    private HBookService service;

    @Test
    public void save() {
        HBook hBook = new HBook();
        hBook.setName("书本名字");
        service.save(hBook);
    }

}
