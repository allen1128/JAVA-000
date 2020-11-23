import com.java.spring.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.PostConstruct;

@Configuration
@Import({StudentConfig.class, ConditionalConfig.class})
@ImportResource("spring-config.xml")
public class BeanConfig {

    @Autowired @Qualifier("student1")
    private Student student1;

    @Autowired @Qualifier("student2")
    private Student student2;

    @Autowired(required = false) @Qualifier("bullyStudent")
    private Student bullyStudent;

    @PostConstruct
    public void post() {
        System.out.print(student1.toString());
        System.out.print(student2.toString());
        if (bullyStudent != null) {
            System.out.print(bullyStudent.toString());
        }
    }
}
