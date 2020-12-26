package io.kimmking.dubbo.demo.api;

import java.io.Serializable;

public class Money implements Serializable {
    private Currency currency;
    private float amount;

    public Money() {}

    public Money(Currency currency, float amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Money(Money money) {
        this.currency = money.currency;
        this.amount = money.amount;
    }


    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
