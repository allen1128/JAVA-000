package com.camp.redis.subpub.order;

import java.io.Serializable;

public class Order implements Serializable {
    private Integer id;
    private Integer commodityId;
    private Integer quantity;

    public Order(Integer id, Integer commodityId, Integer quantity) {
        this.id = id;
        this.commodityId = commodityId;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", commodityId=" + commodityId +
                ", quantity=" + quantity +
                '}';
    }
}
