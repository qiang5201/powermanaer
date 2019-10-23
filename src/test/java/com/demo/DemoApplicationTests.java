package com.demo;

import com.demo.spring.Appconfig;
import com.demo.spring.BeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        AnnotationConfigApplicationContext a =
                new AnnotationConfigApplicationContext(Appconfig.class);
        BeanTest bean = a.getBean(BeanTest.class);
        System.out.println(bean);
    }


}
