package io.kimmking.dubbo.demo.provider;

import io.kimmking.dubbo.demo.api.Account;
import io.kimmking.dubbo.demo.api.Currency;
import io.kimmking.dubbo.demo.api.Money;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        try {
            return (Account) jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setName(resultSet.getString("name"));
                account.setRmbWallet(resultSet.getFloat("rmb_wallet"));
                account.setFrozenRmbWallet(resultSet.getFloat("frozen_rmb_wallet"));
                account.setUsdWallet(resultSet.getFloat("usd_wallet"));
                account.setFrozenUsdWallet(resultSet.getFloat("frozen_usd_wallet"));
                return account;
            });
        } catch (EmptyResultDataAccessException ex) {
            logger.warn("no result found for id={}", id);
            return null;
        }
    }

    public boolean update(Account account, Money from, Money to) {
        final String frozenField = getfrozenCurrencyField(to.getCurrency());
        final String currField = getCurrencyField(from.getCurrency());
        final String sql = "update " + whichTable(account.getId()) +
                "set " + frozenField + " = " + frozenField + " + ?, " +
                currField + " = " + currField + " - ? " +
                " where id = ?";
        logger.info("update sql={}", sql);
        return jdbcTemplate.update(sql, new Object[] {to.getAmount(), from.getAmount(), account.getId()}) > 0;
    }

    public boolean confirm(Account account, Money to) {
        final String frozencurrencyField = getfrozenCurrencyField(to.getCurrency());
        final String currencyField = getCurrencyField(to.getCurrency());

        final String sql = "update " + whichTable(account.getId()) +
                " set " + frozencurrencyField + " = " + frozencurrencyField + " - " + to.getAmount() + ", " +
                currencyField + " = " + currencyField + "+" + to.getAmount() +
                " where id = ? and " + frozencurrencyField + " > 0";
        logger.info("confirm sql={}", sql);
        return jdbcTemplate.update(sql, new Object[] {account.getId()}) > 0;
    }

    public boolean cancel(Account account, Money from, Money to) {
        final String frozencurrencyField = getfrozenCurrencyField(to.getCurrency());
        final String currencyField = getCurrencyField(from.getCurrency());

        final String sql = "update " + whichTable(account.getId()) +
                " set " + frozencurrencyField + " = " + frozencurrencyField + " - " + to.getAmount() + ", " +
                currencyField + " = " + currencyField + "+" + from.getAmount() +
                " where id = ? and " + frozencurrencyField + " > 0";
        logger.info("cancel sql={}", sql);
        return jdbcTemplate.update(sql, new Object[] {account.getId()}) > 0;
    }

    public boolean cancelConfirmed(Account account, Money from, Money to) {
        final String toCurrencyField = getCurrencyField(to.getCurrency());
        final String fromCurrencyField = getCurrencyField(from.getCurrency());

        final String sql = "update " + whichTable(account.getId()) +
                " set " + toCurrencyField + " = " + toCurrencyField + " - " + to.getAmount() + ", " +
                fromCurrencyField + " = " + fromCurrencyField + "+" + from.getAmount() +
                " where id = ? and " + toCurrencyField + " > 0";
        logger.info("cancel sql={}", sql);
        return jdbcTemplate.update(sql, new Object[] {account.getId()}) > 0;
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
}
