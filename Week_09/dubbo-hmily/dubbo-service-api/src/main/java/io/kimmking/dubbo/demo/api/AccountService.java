package io.kimmking.dubbo.demo.api;

public interface AccountService {

    Account findById(int id);

    boolean update(UpdateAccountRequest updateAccountRequest);

    boolean cancel(UpdateAccountRequest updateAccountRequest);

    boolean cancelConfirmed(UpdateAccountRequest updateAccountRequest);

}
