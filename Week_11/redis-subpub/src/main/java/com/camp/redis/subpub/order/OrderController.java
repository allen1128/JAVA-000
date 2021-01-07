package com.camp.redis.subpub.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService publisher;
    @PostMapping("/create")
    public Order create(@RequestParam Integer orderId, @RequestParam Integer commodityId, @RequestParam Integer quantity) {
        return publisher.add(orderId, commodityId, quantity);
    }
}
