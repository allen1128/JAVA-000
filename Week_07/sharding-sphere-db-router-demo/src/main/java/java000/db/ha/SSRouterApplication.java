package java000.db.ha;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class SSRouterApplication {
    public static void main(String[] args) {
        SpringApplication.run(SSRouterApplication.class);
    }
}
