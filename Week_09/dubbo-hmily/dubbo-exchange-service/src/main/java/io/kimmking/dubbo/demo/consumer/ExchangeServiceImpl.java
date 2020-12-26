package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.Account;
import io.kimmking.dubbo.demo.api.AccountService;
import io.kimmking.dubbo.demo.api.Currency;
import io.kimmking.dubbo.demo.api.Money;
import io.kimmking.dubbo.demo.api.UpdateAccountRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private static Logger logger = LogManager.getLogger(ExchangeServiceImpl.class);

    @DubboReference(version = "1.0.0")
    private AccountService accountService;

    @Override
    public float exchange(float amount, Currency from, Currency to) {
        return Float.valueOf(String.format("%.02f", amount * Currency.getRate(from, to)));
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmExchange", cancelMethod = "cancelExchange")
    public String exchange(int fromId, int toId, float amount, Currency fromCurrency, Currency toCurrency) {
        logger.info("trying ==================> ");
        Account fromAccount = accountService.findById(fromId);
        Account toAccount = accountService.findById(toId);
        logger.info("before ==> from={}, to={}", fromAccount, toAccount);
        Money from = new Money(fromCurrency, amount);
        Money to = new Money(toCurrency, exchange(amount, fromCurrency, toCurrency));

        accountService.update(new UpdateAccountRequest(fromAccount, from, to));
        accountService.update(new UpdateAccountRequest(toAccount, to, from));

        fromAccount = accountService.findById(fromId);
        toAccount = accountService.findById(toId);
        String message = String.format("try succeeded ==> from=%s, to=%s", fromAccount, toAccount);
        logger.info(message);
        return message;
    }

    public void confirmExchange(int fromId, int toId, float amount, Currency fromCurrency, Currency toCurrency) {
        logger.info("confirming ==================> ");
    }

    private void validate(float needed, float has) {
        if (needed > has) {
            throw new RuntimeException(
                    String.format("no enough fund: needed=%s, has=%s", needed, has));
        }
    }

    public void cancelExchange(int fromId, int toId, float amount, Currency fromCurrency, Currency toCurrency) {
        logger.info("cancelling ==================> ");
        Account fromAccount = accountService.findById(fromId);
        Money from = new Money(fromCurrency, amount);
        Money to = new Money(toCurrency, exchange(amount, fromCurrency, toCurrency));
        accountService.cancelConfirmed(new UpdateAccountRequest(fromAccount, from, to));

        fromAccount = accountService.findById(toId);
        logger.info("cancel succeeded ==================> fromAccount=%s", fromAccount);
    }
}
