package com.skills.mybatis.myframework.mybatis2.sqlsource;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class ParameterMapping {
    private String name;
    private Class<?> type;

    public ParameterMapping() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public ParameterMapping(String name) {
        this.name =name;
    }
}
