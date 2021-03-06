package com.java000.starter;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore(Student.class)
@ConditionalOnResource(resources = "META-INF/spring.factories")
@EnableConfigurationProperties(Student.class)
public class MyClassConfig {

    @Bean("defaultStudent")
    public Student getStudent() {
        return new Student();
    }

    @Bean("myClass")
    public MyClass getMyClass() {
        MyClass myClass = new MyClass();
        List<Student> studentList = new ArrayList<>();
        studentList.add(getStudent());
        studentList.add(getStudent());
        myClass.setStudents(studentList);
        return myClass;
    }

    @Bean("mySchool")
    public School getSchool() {
        School school = new School();
        List<MyClass> classes = new ArrayList<>();
        classes.add(getMyClass());
        school.setMyClasses(classes);
        return school;
    }
}
