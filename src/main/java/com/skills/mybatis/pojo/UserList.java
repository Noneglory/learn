package com.skills.mybatis.pojo;

import java.util.List;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/04
 */
public class UserList {
    private Integer uid;
    private String uname;
    private Account account;
    private List<Account> accountList;

    public UserList() {
    }

    public UserList(Integer uid, String uname, Account account, List<Account> accountList) {
        this.uid = uid;
        this.uname = uname;
        this.account = account;
        this.accountList = accountList;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        return "UserList{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", account=" + account +
                ", accountList=" + accountList +
                '}';
    }
}
