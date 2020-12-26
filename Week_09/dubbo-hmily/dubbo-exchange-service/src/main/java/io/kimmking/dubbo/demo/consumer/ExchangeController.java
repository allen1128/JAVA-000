package io.kimmking.dubbo.demo.consumer;

import io.kimmking.dubbo.demo.api.Account;
import io.kimmking.dubbo.demo.api.AccountService;
import io.kimmking.dubbo.demo.api.Currency;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExchangeController {

    private static final Logger logger = LogManager.getLogger(ExchangeController.class);

    @Autowired private ExchangeService exchangeService;

    @DubboReference(version = "1.0.0") //, url = "dubbo://127.0.0.1:12345")
    private AccountService accountService;

    @PostMapping("/exchange")
    public String exchange(@RequestParam int from,
                           @RequestParam int to,
                           @RequestParam float amount,
                           @RequestParam Currency fromEquityType,
                           @RequestParam Currency toEquityType) {

        return exchangeService.exchange(from, to, amount, fromEquityType, toEquityType);
    }

    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable Integer id) {
        Account account = accountService.findById(id);
        return account;
    }

    @GetMapping("/currency/list")
    public List<Currency> getCurrencyList() {
        return Arrays.stream(Currency.values()).collect(Collectors.toList());
    }
}
