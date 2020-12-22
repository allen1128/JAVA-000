package io.kimmking.dubbo.demo.provider;

import io.kimmking.dubbo.demo.api.Account;
import io.kimmking.dubbo.demo.api.AccountService;
import io.kimmking.dubbo.demo.api.Money;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService(version = "1.0.0")
public class AccountServiceImpl implements AccountService {
    private final static Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    @Autowired AccountDao accountDao;

    @Override
    public Account findById(int id) {
        Account account = accountDao.findById(id);
        logger.info("account={}" , account);
        return account;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean update(Account account, Money from, Money to) {
        accountDao.add(account, to);
        accountDao.deduct(account, from);
        return true;
    }

    @Override
    public boolean confirm(Account account, Money from, Money to) {
        accountDao.confirm(account, from.getCurrency());
        accountDao.confirm(account, to.getCurrency());
        return true;
    }

    @Override
    public boolean cancel(Account account, Money from, Money to) {
        accountDao.cancel(account, from.getCurrency());
        accountDao.cancel(account, to.getCurrency());
        return true;
    }

}
