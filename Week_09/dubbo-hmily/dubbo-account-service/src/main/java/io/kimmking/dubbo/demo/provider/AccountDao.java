package io.kimmking.dubbo.demo.provider;

import io.kimmking.dubbo.demo.api.Account;
import io.kimmking.dubbo.demo.api.Currency;
import io.kimmking.dubbo.demo.api.Money;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class AccountDao {
    private static final Logger logger = LogManager.getLogger(AccountDao.class);

    @Autowired private JdbcTemplate jdbcTemplate;

    public Account findById(int id) {
        final String sql ="select * from " + whichTable(id) + " where id = ?";
        List<Account> accountList = jdbcTemplate.query(sql, new Object[] {id}, new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet resultSet, int i) throws SQLException {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setName(resultSet.getString("name"));
                account.setRmbWallet(resultSet.getFloat("rmb_wallet"));
                account.setFrozenRmbWallet(resultSet.getFloat("frozen_rmb_wallet"));
                account.setUsdWallet(resultSet.getFloat("usd_wallet"));
                account.setFrozenUsdWallet(resultSet.getFloat("frozen_usd_wallet"));
                return account;
            }
        });
        return accountList.size() == 0 ? null : accountList.get(0);
    }

    public boolean add(Account account, Money money) {
        return update(account, money);
    }

    public boolean deduct(Account account, Money money) {
        Money newmoney = new Money(money);
        newmoney.setAmount(-newmoney.getAmount());
        return update(account, newmoney);
    }

    public boolean confirm(Account account, Currency currency) {
        final String frozencurrencyField = getfrozenCurrencyField(currency);
        final String sql = "update " + whichTable(account.getId()) +
                " set " + frozencurrencyField + " =  0 " +
                "where id = ?";
        return jdbcTemplate.update(sql, account.getId()) > 0;
    }

    public boolean cancel(Account account, Currency currency) {
        final String frozencurrencyField = getfrozenCurrencyField(currency);
        final String currencyField = getCurrencyField(currency);

        final String sql = "update " + whichTable(account.getId()) +
                "set " + frozencurrencyField + " =  0, " +
                "set " + currencyField + " = frozencurrencyField + currencyField " +
                "where id = ?";
        return jdbcTemplate.update(sql, account.getId()) > 0;
    }

    private String whichTable(int id) {
        return " account" + (id % 2 == 1 ? "_odd " : "_even ");
    }

    private String getfrozenCurrencyField(Currency currency) {
        return "frozen_" + getCurrencyField(currency);
    }

    private String getCurrencyField(Currency currency) {
        return currency.toString().toLowerCase() + "_wallet";
    }

    private boolean update(Account account, Money money) {
        final String frozenCurrField = getfrozenCurrencyField(money.getCurrency());
        final String currField = getCurrencyField(money.getCurrency());
        final String sql = "update " + whichTable(account.getId()) +
                "set " + frozenCurrField + " = " + frozenCurrField + " + ?, " +
                "set " + currField + " = " + currField + " - ? " +
                "where id = ?";
        return jdbcTemplate.update(sql, money.getAmount(), money.getAmount(), account.getId()) > 0;

    }
}
