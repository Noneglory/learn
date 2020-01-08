package com.skills.mybatis.myframework.mybatis2;

import com.skills.mybatis.myframework.mybatis2.factory.iface.SqlSession;
import com.skills.mybatis.myframework.mybatis2.factory.iface.SqlSessionFactory;
import com.skills.mybatis.myframework.mybatis2.factory.SqlSessionFactoryBuilder;
import com.skills.mybatis.myframework.mybatis2.io.Resources;
import com.skills.mybatis.myframework.mybatis2.po.User;

import java.io.InputStream;
import java.util.List;


/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public class Entrance {

    public static void main(String args[]){
        String path = "mybatis/myframework/mybatis-config.xml";
        InputStream inputStream = Resources.getResourcesAsStream(path);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder(inputStream);
        SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build();
        SqlSession  sqlSession  = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(1);
        user.setUsername("王五");
        /**
         * findUserList
         */
        List<User> userList = sqlSession.selectList("test.findUserById",user);
        System.out.println(userList);
        List<User> userList2 = sqlSession.selectList("test.findUserList",user);
        System.out.println(userList2);
    }
}
