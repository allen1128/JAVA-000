package com.java000.jdbc;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentDao {

    @Autowired private DataSource dataSource;
    private PreparedStatement preparedStatement;
    private ResultSet rs;

    @PostConstruct
    public void postConstruct(){
        String sql = "create table student (id int primary key, name varchar(256))";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(Student[] students) {
        StringBuilder sb = new StringBuilder("insert into student(id, name) ");
        for (int i = 0; i < students.length; i++) {
           sb.append("values(?, ?)");
        }

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(sb.toString());
            int counter = 0;
            for (int i = 0; i < students.length; i++) {
                preparedStatement.setInt(++counter, students[i].getId());
                preparedStatement.setString(++counter, students[i].getName());
            }
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void add (Student student) {
        String sql = "insert into student(id, name) values(?, ?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int studentId) {
        String sql = "delete from student where id = ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Student student) {
        String sql = "update student set name=? where id = ?";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Student find(int studentId) {
        String sql = "select id, name from student where id = ?";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, studentId);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
