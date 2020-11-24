package com.java000.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = BeanConfig.class)
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    void add() {
        Student student = new Student(3, "soso student");
        studentService.add(student);
        Student studentNew = studentService.find(3);
        System.out.println(studentNew);
    }

    @Test
    void delete() {
        studentService.delete(3);
        Student studentNew = studentService.find(3);
        System.out.println(studentNew);
    }

    @Test
    void update() {
        Student student = new Student(2, "new student");
        studentService.add(student);
        student = studentService.find(2);
        student.setName("updated name");
        studentService.update(student);
        Student studentNew = studentService.find(2);
        System.out.println(studentNew);
    }

    @Test
    void batchAdd() {
        studentService.add(new Student(4, "student4"));
        studentService.add(new Student(5, "student5"));
        Student student4 = studentService.find(4);
        Student student5 = studentService.find(5);
        System.out.println(student4);
        System.out.println(student5);
    }
}