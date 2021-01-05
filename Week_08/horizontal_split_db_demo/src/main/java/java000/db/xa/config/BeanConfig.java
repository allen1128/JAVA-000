package java000.db.xa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BeanConfig {

    @Bean(name="dataSource")
    @ConfigurationProperties(prefix="spring.datasource" )
    public DataSource getMasterDataSource() {
        return DataSourceBuilder.create().build();
    }

}
