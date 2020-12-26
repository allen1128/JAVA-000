package io.kimmking.dubbo.demo.api;

import java.io.Serializable;

public class UpdateAccountRequest implements Serializable {
    private Account account;

    private Money from;
    private Money to;

    public UpdateAccountRequest() {}

    public UpdateAccountRequest(Account account, Money from, Money to) {
        this.account = account;
        this.from = from;
        this.to = to;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Money getFrom() {
        return from;
    }

    public void setFrom(Money from) {
        this.from = from;
    }

    public Money getTo() {
        return to;
    }

    public void setTo(Money to) {
        this.to = to;
    }
}
