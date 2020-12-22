package io.kimmking.dubbo.demo.api;

public class Account implements java.io.Serializable {

    private int id;
    private String name;
    private float usdWallet;
    private float frozenUsdWallet;
    private float rmbWallet;
    private float frozenRmbWallet;

    public Account(){}

    public Account(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getUsdWallet() {
        return usdWallet;
    }

    public void setUsdWallet(float usdWallet) {
        this.usdWallet = usdWallet;
    }

    public float getRmbWallet() {
        return rmbWallet;
    }

    public void setRmbWallet(float rmbWallet) {
        this.rmbWallet = rmbWallet;
    }

    public float getFrozenUsdWallet() {
        return frozenUsdWallet;
    }

    public void setFrozenUsdWallet(float frozenUsdWallet) {
        this.frozenUsdWallet = frozenUsdWallet;
    }

    public float getFrozenRmbWallet() {
        return frozenRmbWallet;
    }

    public void setFrozenRmbWallet(float frozenRmbWallet) {
        this.frozenRmbWallet = frozenRmbWallet;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", usdWallet=" + usdWallet +
                ", frozenUsdWallet=" + frozenUsdWallet +
                ", rmbWallet=" + rmbWallet +
                ", frozenRmbWallet=" + frozenRmbWallet +
                '}';
    }
}
