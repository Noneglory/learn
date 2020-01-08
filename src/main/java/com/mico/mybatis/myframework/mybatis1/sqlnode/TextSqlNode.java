package com.mico.mybatis.myframework.mybatis1.sqlnode;

import com.mico.mybatis.myframework.mybatis1.sqlnode.iface.SqlNode;
import com.mico.mybatis.myframework.mybatis1.handler.BindingTokenParser;
import com.mico.mybatis.myframework.mybatis1.utils.GenericTokenParser;

/**
 * describe: 只要sql语句中有${}就会封装到该类里面
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class TextSqlNode  implements SqlNode {
    private String sqlText;

    public TextSqlNode(String sqlText) {
        this.sqlText = sqlText;
    }

    public boolean isDynamic() {
        if(sqlText.indexOf("${")>-1)
        {
            return  true;
        }
        return false;
    }
    //${}的处理，就是这个时候
    @Override
    public void apply(DynamicContext context) {
        BindingTokenParser handler = new BindingTokenParser(context);
        //将${}
        GenericTokenParser tokenParser = new GenericTokenParser("${","}",handler);
        //获取真正可以执行的SQL语句
        String sql = tokenParser.parse(sqlText);
        context.appendSql(sql);
    }


}
