package java000.db.ha;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DBRouterApplication {
    public static void main(String[] args) {
        SpringApplication.run(DBRouterApplication.class);
    }
}
