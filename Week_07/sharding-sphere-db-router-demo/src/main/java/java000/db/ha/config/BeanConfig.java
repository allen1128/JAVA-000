package java000.db.ha.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;

@Configuration
public class BeanConfig {
    @Bean
    public DataSource dataSource() throws FileNotFoundException, SQLException, IOException {
        return MasterSlaveDataSourceFactory.createDataSource(ResourceUtils.getFile("classpath:sharding-jdbc.yml"));
    }
}
