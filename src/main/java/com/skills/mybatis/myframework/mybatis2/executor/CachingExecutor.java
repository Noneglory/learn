package com.skills.mybatis.myframework.mybatis2.executor;

import com.skills.mybatis.myframework.mybatis2.config.Configuration;
import com.skills.mybatis.myframework.mybatis2.config.MappedStatement;

/**
 * describe:二级缓存
 *
 * @author leijiang
 * @date 2020/01/08
 */
public class CachingExecutor implements Executor {
    //
    private Executor delegate = new SimpleExecutor();
    @Override
    public Object query(Configuration configuration, MappedStatement mappedStatement, Object param) {
        //TODO 有二级缓存的话，二级缓存处理，二级没有，一级有，一级处理，二级没有，一级没有，通过数据库进行处理。
        return delegate.query( configuration,mappedStatement,param);
    }
}
