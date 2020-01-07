package com.mico.mybatis.myframework.mybatis1.sqlsource;

import com.mico.mybatis.myframework.mybatis1.handler.ParameterMappingTokenHandler;
import com.mico.mybatis.myframework.mybatis1.sqlnode.DynamicContext;
import com.mico.mybatis.myframework.mybatis1.sqlnode.iface.SqlNode;
import com.mico.mybatis.myframework.mybatis1.utils.GenericTokenParser;

/**
 * describe:封装带有${}或者动态SQL标签的SQL信息
 * 对可执行的sql以及所需的参数列表进行封装
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class DynamicSqlSource implements SqlSource {
    private SqlNode rootSqlNode;
    public DynamicSqlSource(SqlNode rootSqlnode) {
        this.rootSqlNode=rootSqlnode;
    }


    @Override
    public BoundSql getBoundSql(Object param) {

        DynamicContext context = new DynamicContext(param);
        //处理sqlNode，先去处理动态标签和${}，拼接成一条SQL文本，该SQL文本还包括#{}
        rootSqlNode.apply(context);

        //处理#{}
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        //将#{}解析为？并保存参数信息
        GenericTokenParser tokenParser = new GenericTokenParser("#{","}",handler);
        //获取真正可以执行的sql语句
        String sql = tokenParser.parse(context.getSql());
        return new BoundSql(sql,handler.getParameterMappings());
    }
}
