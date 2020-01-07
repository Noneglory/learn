package com.mico.mybatis.myframework.mybatis1.sqlnode;

import com.mico.mybatis.myframework.mybatis1.sqlnode.iface.SqlNode;

/**
 * describe:纯文本sql语句(可能带有#{})
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class StaticTextSqlNode implements SqlNode {
    private String sqlText;
    public StaticTextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }


    @Override
    public void apply(DynamicContext context) {

        context.appendSql(sqlText);
    }
}
