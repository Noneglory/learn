package com.skills.mybatis.myframework.mybatis2.factory;

import com.skills.mybatis.myframework.mybatis2.config.Configuration;
import com.skills.mybatis.myframework.mybatis2.factory.iface.DefaultSqlSession;
import com.skills.mybatis.myframework.mybatis2.factory.iface.SqlSession;
import com.skills.mybatis.myframework.mybatis2.factory.iface.SqlSessionFactory;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
