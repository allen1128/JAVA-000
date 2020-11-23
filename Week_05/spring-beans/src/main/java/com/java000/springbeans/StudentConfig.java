import org.springframework.context.annotation.Bean;

public class StudentConfig {
    @Bean(name="student2")
    public Student getStudent2() {
        return new Student(2, "Ura");
    }
}
