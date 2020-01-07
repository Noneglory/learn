package com.mico.mybatis.dao;

import com.mico.mybatis.Iface.IAccount;
import com.mico.mybatis.pojo.Account;
import com.mico.mybatis.pojo.User;
import com.mico.mybatis.pojo.UserList;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 纯mybatis的开发测试
 */
public class SqlSessionInstanceTest {

    @Test
    public void getSqlSession() {
        SqlSession sqlSession = SqlSessionInstance.getSqlSession();
        System.out.println(sqlSession);

        IAccount mapper = sqlSession.getMapper(IAccount.class);
        mapper.accountInsert(new Account(6,1,"浅浅",2));
//        sqlSession.commit(true);
        sqlSession.commit();
    }

    @Test
    public void getAccountByIdTest() {
        SqlSession sqlSession = SqlSessionInstance.getSqlSession();
        System.out.println(sqlSession);

        IAccount mapper = sqlSession.getMapper(IAccount.class);
        Account accountById = mapper.getAccountById(1);
        System.out.println(accountById);
    }

    @Test
    public void getUserAccountTest(){
        SqlSession sqlSession = SqlSessionInstance.getSqlSession();
        System.out.println(sqlSession);

        IAccount mapper = sqlSession.getMapper(IAccount.class);
        UserList userListById = mapper.getUserAccount(1);
        System.out.println(userListById);
    }

    @Test
    public void getUserListByIdTest(){
        SqlSession sqlSession = SqlSessionInstance.getSqlSession();
        System.out.println(sqlSession);

        IAccount mapper = sqlSession.getMapper(IAccount.class);
        UserList userListById = mapper.getUserListById(1);
        System.out.println(userListById);
    }


    @Test
    public void batchUserDeleteTest(){
        SqlSession sqlSession = SqlSessionInstance.getSqlSession();
        System.out.println(sqlSession);

        IAccount mapper = sqlSession.getMapper(IAccount.class);
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        mapper.batchUserDelete(list);
        sqlSession.commit();
    }

    @Test
    public void batchUserInsertTest(){
        SqlSession sqlSession = SqlSessionInstance.getSqlSession();
        System.out.println(sqlSession);

        IAccount mapper = sqlSession.getMapper(IAccount.class);
        List<User> list = new ArrayList<User>();
        User user = new User(6,"xixi");
        list.add(user);
        mapper.batchUserInsert(list);
        sqlSession.commit();
    }

    @Test
    public void UserUpdateByUserTest(){
        SqlSession sqlSession = SqlSessionInstance.getSqlSession();
        System.out.println(sqlSession);

        IAccount mapper = sqlSession.getMapper(IAccount.class);

        User user = new User(6,"haha");

        mapper.UserUpdateByUser(user);
        sqlSession.commit();
    }


    @Test
    public void accountAddmoneyTest(){
        SqlSession sqlSession = SqlSessionInstance.getSqlSession();
        System.out.println(sqlSession);
        IAccount mapper = sqlSession.getMapper(IAccount.class);
        Account account = new Account(1,2,"fasd",11);
        mapper.accountAddmoney(account,100);
        sqlSession.commit();
    }

    @Test
    public void accountSubmoneyTest(){
        SqlSession sqlSession = SqlSessionInstance.getSqlSession();
        IAccount mapper = sqlSession.getMapper(IAccount.class);
        Account account = new Account(1,2,"fasd",11);
        mapper.accountSubmoney(account,100);
        sqlSession.commit();
    }




}