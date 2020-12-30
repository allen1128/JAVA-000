package java000.db.ha.order;

import java000.db.ha.datasource.ReadOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    @ReadOnly
    public Order findById(int id) {
        return orderDao.findById(id);
    }

    public void add(Order order) {
        orderDao.add(order);
    }
}
