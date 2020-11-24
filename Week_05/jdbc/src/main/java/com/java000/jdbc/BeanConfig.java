package com.java000.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(value = "com.java000.jdbc")
@EnableConfigurationProperties(DataSourceProperties.class)
public class BeanConfig {

    @Bean(name="dataSource")
    @ConfigurationProperties(prefix="spring.datasource" )
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public StudentDao getStudentDao() {
        return new StudentDao();
    }

    @Bean
    public StudentService getStudentService() {
        return new StudentService();
    }

}
