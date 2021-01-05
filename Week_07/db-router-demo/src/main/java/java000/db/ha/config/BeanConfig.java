package java000.db.ha.config;

import java000.db.ha.datasource.DataSourceType;
import java000.db.ha.datasource.RoutingDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BeanConfig {

    @Bean(name="masterDataSource")
    @ConfigurationProperties(prefix="spring.datasource.master" )
    public DataSource getMasterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="slaveDataSource1")
    @ConfigurationProperties(prefix="spring.datasource.slave1" )
    public DataSource getSlaveDataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="slaveDataSource2")
    @ConfigurationProperties(prefix="spring.datasource.slave2" )
    public DataSource getSlaveDataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public RoutingDataSource getRoutingDataSource() {
        Map<Object, Object> map = new HashMap<>();
        map.put(DataSourceType.WRITE, getMasterDataSource());
        map.put(DataSourceType.READ_1, getSlaveDataSource1());
        map.put(DataSourceType.READ_2, getSlaveDataSource1());
        return new RoutingDataSource(getMasterDataSource(), map);
    }
}
