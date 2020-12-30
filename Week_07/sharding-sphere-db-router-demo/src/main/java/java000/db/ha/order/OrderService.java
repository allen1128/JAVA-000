package java000.db.ha.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    public Order findById(int id) {
        return orderDao.findById(id);
    }

    public void add(Order order) {
        orderDao.add(order);
    }
}
