package com.mico.mybatis.pojo;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/03
 */
public class Account {
    private  int id;
    private int uid;
    private  String name;
    private int money;

    public Account() {
    }

    public Account(int id, int uid, String name, int money) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
