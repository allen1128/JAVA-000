package com.java000.springbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.PostConstruct;

@Configuration
@Import({StudentConfig.class, ConditionalConfig.class})
@ImportResource("spring-config.xml")
public class BeanConfig {

    @Autowired @Qualifier("goodStudent")
    private Student goodStudent;

    @Autowired @Qualifier("badStudent")
    private Student badStudent;

    @Autowired(required = false) @Qualifier("bullyStudent")
    private Student bullyStudent;

    @PostConstruct
    public void postConstruct() {
        System.out.print(goodStudent.toString());
        System.out.print(badStudent.toString());
        if (bullyStudent != null) {
            System.out.print(bullyStudent.toString());
        }
    }
}
