package java000.db.ha.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired private OrderService orderService;

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id) {
        if (id == null) {
            return null;
        }
        return orderService.findById(id);
    }

    @PostMapping("/add")
    public String add(@RequestParam Integer orderId, @RequestParam Integer userId, @RequestParam Integer commodityId, @RequestParam Integer quantity) {
        Order order = new Order(orderId, userId, commodityId, quantity);
        order.setUpdated_ts(LocalDateTime.now());
        orderService.add(order);
        return "added order=" + orderId + " successfully";
    }
}
