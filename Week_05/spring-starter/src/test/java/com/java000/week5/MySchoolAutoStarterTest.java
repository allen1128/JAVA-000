package com.java000.week5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MyClassConfig.class)
public class MySchoolAutoStarterTest {

    @Autowired private School mySchool;

    @Test
    public void test() {
        System.out.println(mySchool.toString());
    }
}
