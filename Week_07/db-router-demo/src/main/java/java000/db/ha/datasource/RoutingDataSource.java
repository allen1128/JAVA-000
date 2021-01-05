package java000.db.ha.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

public class RoutingDataSource extends AbstractRoutingDataSource {

    private final static ThreadLocal<java000.db.ha.datasource.DataSourceType> dataSourceThreadLocal = new ThreadLocal<>();

    public RoutingDataSource(DataSource dataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(dataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDatasource();
    }

    public static Object getDatasource() {
        Object object = dataSourceThreadLocal.get();
        System.out.println("picked datasource=" + object);
        return object;
    }

    public static void setDataSource(DataSourceType currDataSource) {
        dataSourceThreadLocal.set(currDataSource);
    }

    public static void clearDatasource() {
        dataSourceThreadLocal.remove();
    }



}
