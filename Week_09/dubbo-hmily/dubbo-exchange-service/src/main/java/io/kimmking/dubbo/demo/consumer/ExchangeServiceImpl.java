package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.Account;
import io.kimmking.dubbo.demo.api.AccountService;
import io.kimmking.dubbo.demo.api.Currency;
import io.kimmking.dubbo.demo.api.Money;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private static Logger logger = LogManager.getLogger(ExchangeServiceImpl.class);

    @DubboReference(version = "1.0.0")
    private AccountService accountService;

    @Override
    public float exchange(float amount, Currency from, Currency to) {
        return amount * Currency.getRate(from, to);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmExchange", cancelMethod = "cancelExchange")
    public String exchange(int fromId, int toId, float amount, Currency fromCurrency, Currency toCurrency) {

        Account fromAccount = accountService.findById(fromId);

        Account toAccount = accountService.findById(toId);

        logger.info("from exchange, fromId: {}, toId: {}", fromAccount, toAccount);

        Money from = new Money(fromCurrency, amount);

        Money to = new Money(toCurrency, exchange(amount, fromCurrency, toCurrency));

        accountService.update(fromAccount, from, to);

        accountService.update(toAccount, to, from);

        String message = String.format("to exchange, fromId: %s, toId: %s", fromAccount, toAccount);

        logger.info(message);

        return message;
    }

    private Money getMoney(float amount, Currency currency) {
        return new Money(currency, amount);
    }

    private boolean deduct(Account account, float amount, Currency currency) {
        switch (currency) {
            case USD:
                validate(amount, account.getUsdWallet());
                account.setUsdWallet(amount);
                return true;
            case RMB:
                validate(amount, account.getRmbWallet());
                account.setRmbWallet(amount);
                return true;
        }
        return false;
    }

    public void confirmExchange(int fromId, int toId, float amount, Currency fromCurrency, Currency toCurrency) {
        logger.info("confirming on exchange service");
    }

    private void validate(float needed, float has) {
        if (needed > has) {
            throw new RuntimeException(
                    String.format("no enough fund: needed=%s, has=%s", needed, has));
        }
    }

    public void cancelExchange(int fromId, int toId, float amount, Currency fromCurrency, Currency toCurrency) {
        logger.info("cancelling on exchange service");
    }
}
