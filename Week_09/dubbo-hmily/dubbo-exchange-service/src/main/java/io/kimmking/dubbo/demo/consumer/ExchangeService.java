package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.Currency;

public interface ExchangeService {

    float exchange(float amount, Currency from, Currency to);

    String exchange(int fromAccount, int toAccount, float amount, Currency fromCurrency, Currency toCurrency);

}
