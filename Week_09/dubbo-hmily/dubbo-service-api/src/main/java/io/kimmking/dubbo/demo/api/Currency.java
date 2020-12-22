package io.kimmking.dubbo.demo.api;

import java.io.Serializable;

public enum Currency implements Serializable {
    RMB(1.0f),
    USD(7.0f),
    ;

    float value;

    Currency(float value) {
        this.value = value;
    }

    public static float getRate(Currency from, Currency to) {
        return from.value / to.value;
    }

}
