package com.mico.mybatis.myframework.mybatis1.sqlsource;

import java.util.ArrayList;
import java.util.List;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class StaticSqlSource implements SqlSource {
    // 解析之后的SQL语句
    private String sql;

    // 解析过程中产生的SQL参数信息
    private List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();


    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    @Override
    public BoundSql getBoundSql(Object param) {

        return new BoundSql(sql, parameterMappings);
    }
}
