package com.mico.mybatis.pojo;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/04
 */
public class User {
    private Integer uid;
    private String uname;

    public User() {
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

    public User(Integer uid, String uname) {
        this.uid = uid;
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                '}';
    }
}
