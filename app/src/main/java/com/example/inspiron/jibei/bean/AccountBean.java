package com.example.inspiron.jibei.bean;

public class AccountBean {
    private int id;

    //账本名称
    private String accountName;
    //绑定id
    private String bindingId;


    public String getBindingId() {
        return bindingId;
    }

    public void setBindingId(String bindingId) {
        this.bindingId = bindingId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

}
