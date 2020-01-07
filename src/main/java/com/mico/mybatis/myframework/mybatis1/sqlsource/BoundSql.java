package com.mico.mybatis.myframework.mybatis1.sqlsource;

import java.util.ArrayList;
import java.util.List;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class BoundSql {
    private String sql;
    private List<ParameterMapping> ParameterMapping =new ArrayList<>();

    public BoundSql(String sql, List<ParameterMapping> parameterMapping) {
        this.sql = sql;
        this.ParameterMapping = parameterMapping;
    }



    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return ParameterMapping;
    }

    public void addParametetMappings(ParameterMapping parametetMapping) {
        this.ParameterMapping.add(parametetMapping);
    }
}
