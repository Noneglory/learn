package com.skills.mybatis.myframework.mybatis2.executor;

import com.skills.mybatis.myframework.mybatis2.config.Configuration;
import com.skills.mybatis.myframework.mybatis2.config.MappedStatement;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public abstract  class BaseExecutor implements Executor {
    @Override
    public Object query(Configuration configuration, MappedStatement mappedStatement, Object param) {
        //TODO 如果有一级缓存，则从一级缓存中去取，若没有则直接从数据库中进行执行。
        return doQuery(configuration,  mappedStatement,  param);
    }
    public  abstract  Object doQuery(Configuration configuration, MappedStatement mappedStatement, Object param);
}
