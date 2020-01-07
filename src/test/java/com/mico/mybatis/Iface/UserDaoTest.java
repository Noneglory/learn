package com.mico.mybatis.Iface;

import com.mico.mybatis.basic;
import com.mico.mybatis.pojo.Account;
import com.mico.mybatis.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * mybatis集成spring，以及c3p0,主句库主从结构，动态路由测试。
 */
public class UserDaoTest extends basic {
    @Autowired
    private UserDao userDao;

    @Test
    public void queryUserTest(){
        List<User> users = userDao.queryUser();
        users.forEach(user -> System.out.println(user));
    }


    @Test
    public void accountAddmoneyTest(){
        Account account = new Account();
        account.setId(1);
       userDao.updateaccountAddmoney(account,200);
    }

    @Test
    public void accountSubmoneyTest(){
        Account account = new Account();
        account.setId(1);
        userDao.updateaccountSubmoney(account,200);
    }
}