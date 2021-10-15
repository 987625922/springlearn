package com;

import com.learn.SpringMqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringMqApplication.class)
public class TestUser {

    @Test
    public void test() {
        System.out.println("=======>>>");
    }
}
