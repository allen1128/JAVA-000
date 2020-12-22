package io.kimmking.dubbo.demo.provider;

import io.kimmking.dubbo.demo.api.Account;
import io.kimmking.dubbo.demo.api.AccountService;
import io.kimmking.dubbo.demo.api.Currency;
import io.kimmking.dubbo.demo.api.Money;
import org.apache.dubbo.common.utils.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Autowired private AccountService accountService;

	@GetMapping("/user/{id}")
    public Account findAccount(int id) {
	    return accountService.findById(id);
    }
}
