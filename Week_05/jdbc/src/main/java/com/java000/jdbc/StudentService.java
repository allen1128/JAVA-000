package com.java000.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public void add(List<Student> studentList) {
        studentDao.add(studentList.stream().toArray(Student[]::new));
    }

    public void add(Student student) {
        studentDao.add(student);
    }

    public void delete(int studentId) {
        studentDao.delete(studentId);
    }

    public void update(Student student) {
        studentDao.update(student);
    }

    public Student find(int studentId) {
        return studentDao.find(studentId);
    }
}