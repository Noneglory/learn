package com.skills.mybatis.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获取sqlsession
 */
public class SqlSessionInstance {
    private static SqlSession sqlSession=null;
    public static synchronized SqlSession getSqlSession(){
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis/Configuration.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlSession;
    }
}
