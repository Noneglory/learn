package com.skills.mybatis.myframework.mybatis2.factory.iface;


import com.skills.mybatis.myframework.mybatis2.config.Configuration;
import com.skills.mybatis.myframework.mybatis2.config.MappedStatement;
import com.skills.mybatis.myframework.mybatis2.executor.CachingExecutor;
import com.skills.mybatis.myframework.mybatis2.executor.Executor;

import java.util.List;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public  class DefaultSqlSession implements  SqlSession {
    private Configuration configuration;
    private Executor executor =new CachingExecutor();

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        return (List<T>) executor.query(configuration,mappedStatement,param);
    }
}
