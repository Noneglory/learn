package com.skills.mybatis.myframework.mybatis2.executor;

import com.skills.mybatis.myframework.mybatis2.config.Configuration;
import com.skills.mybatis.myframework.mybatis2.config.MappedStatement;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public interface Executor {
    Object query(Configuration configuration, MappedStatement mappedStatement, Object param);
}
