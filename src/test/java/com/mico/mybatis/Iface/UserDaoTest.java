package com.mico.mybatis.Iface;

import com.mico.mybatis.basic;
import com.mico.mybatis.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest extends basic {
    @Autowired
    private UserDao userDao;

    @Test
    public void queryUserTest(){
        List<User> users = userDao.queryUser();
        users.forEach(user -> System.out.println(user));
    }
}