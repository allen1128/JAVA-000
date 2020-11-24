package com.java000.springbeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

public class ConditionalConfig {

    @Bean(name="bullyStudent")
    @Conditional(StudentCondition.class)
    public Student createdMoreStudent() {
        return new Student(3, "bully student");
    }
}
