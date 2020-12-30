package java000.db.ha.datasource;

import java.util.concurrent.atomic.AtomicInteger;

public class RoutingRoundRobin {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private static final DataSourceType[] datasourceTypes
            = new DataSourceType[] {DataSourceType.READ_1, DataSourceType.READ_2};

    public static DataSourceType next() {
        return datasourceTypes[counter.getAndIncrement() % datasourceTypes.length];
    }
}
