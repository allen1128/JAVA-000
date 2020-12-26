package io.kimmking.dubbo.demo.provider;

import io.kimmking.dubbo.demo.api.Account;
import io.kimmking.dubbo.demo.api.AccountService;
import io.kimmking.dubbo.demo.api.Money;
import io.kimmking.dubbo.demo.api.UpdateAccountRequest;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UpdateAccountRequest updateAccountRequest) {
        validate(updateAccountRequest.getAccount(), updateAccountRequest.getFrom());
        accountDao.update(updateAccountRequest.getAccount(), updateAccountRequest.getFrom(), updateAccountRequest.getTo());
        return true;
    }

    public boolean confirm(UpdateAccountRequest updateAccountRequest) {
        logger.info("account service confirming ===>");
        accountDao.confirm(updateAccountRequest.getAccount(), updateAccountRequest.getTo());
        logger.info("account service confirmed ===>");
        return true;
    }

    @Override
    public boolean cancel(UpdateAccountRequest updateAccountRequest) {
        logger.info("account service cancelling ===>");
        accountDao.cancel(updateAccountRequest.getAccount(), updateAccountRequest.getFrom(), updateAccountRequest.getTo());
        logger.info("account service cancelled ===>");
        return true;
    }

    @Override
    public boolean cancelConfirmed(UpdateAccountRequest updateAccountRequest) {
        logger.info("account service cancelling ===>");
        accountDao.cancelConfirmed(updateAccountRequest.getAccount(), updateAccountRequest.getFrom(), updateAccountRequest.getTo());
        logger.info("account service cancelled ===>");
        return true;
    }

    private void validate(Account account, Money toBeDeducted) {
        switch (toBeDeducted.getCurrency()) {
            case RMB:
                if (account.getRmbWallet() < toBeDeducted.getAmount()) {
                    throw new RuntimeException("no enough balance in account=" + account);
                }
                break;
            case USD:
                if (account.getUsdWallet() < toBeDeducted.getAmount()) {
                    throw new RuntimeException("no enough balance in account=" + account);
                }
                break;
        }
    }

}
