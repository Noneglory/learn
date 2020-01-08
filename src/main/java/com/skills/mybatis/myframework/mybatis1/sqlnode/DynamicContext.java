package com.skills.mybatis.myframework.mybatis1.sqlnode;

import java.util.HashMap;

/**
 * describe:动态上下文，就是封装的入参信息，解析过程中的SQL信息
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class DynamicContext {
    private StringBuffer sb = new StringBuffer();

    private HashMap<String,Object> bindings = new HashMap<>();

    public DynamicContext(Object param)
    {
        bindings.put("_parameter",param);
    }

    public String getSql() {
        return sb.toString();
    }

    public void appendSql(String sqlText){
        this.sb.append(sqlText);
        this.sb.append(" ");
    }

    public HashMap<String, Object> getBindings() {
        return bindings;
    }

    public void addBindings(String name,Object param) {
        this.bindings.put(name,param);
    }
}
