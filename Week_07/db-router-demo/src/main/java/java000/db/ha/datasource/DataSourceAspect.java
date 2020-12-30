package java000.db.ha.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Pointcut("@annotation(java000.db.ha.datasource.ReadOnly)")
    public void readOnly() {}

    @Around("readOnly()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        RoutingDataSource.setDataSource(RoutingRoundRobin.next());
        try {
            return pjp.proceed();
        } finally {
            RoutingDataSource.clearDatasource();
        }
    }

}
