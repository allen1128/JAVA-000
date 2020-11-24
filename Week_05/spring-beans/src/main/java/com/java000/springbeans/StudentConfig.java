package com.java000.springbeans;

import org.springframework.context.annotation.Bean;

public class StudentConfig {
    @Bean(name="goodStudent")
    public Student getStudent2() {
        return new Student(2, "good student");
    }
}
