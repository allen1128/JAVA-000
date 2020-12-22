package io.kimmking.dubbo.demo.api;

public interface AccountService {

    Account findById(int id);

    boolean update(Account account, Money from, Money to);

    boolean confirm(Account account, Money from, Money to);

    boolean cancel(Account account, Money from, Money to);

}
