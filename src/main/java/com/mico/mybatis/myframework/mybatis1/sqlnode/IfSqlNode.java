package com.mico.mybatis.myframework.mybatis1.sqlnode;

import com.mico.mybatis.myframework.mybatis1.sqlnode.iface.SqlNode;
import com.mico.mybatis.myframework.mybatis1.utils.OgnlUtils;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class IfSqlNode implements SqlNode {
    private String test;
    private SqlNode rootSqlNode;

    public IfSqlNode() {
    }

    public IfSqlNode(String test, SqlNode rootSqlNode) {
        this.test = test;
        this.rootSqlNode = rootSqlNode;
    }

    @Override
    public void apply(DynamicContext context) {
        boolean evaluatieBoolean = OgnlUtils.evaluateBoolean(test,context.getBindings().get("_parameter"));
        if(evaluatieBoolean){
            rootSqlNode.apply(context);
           // context.appendSql(context.getSql());
        }
    }
}
