package com.skills.mybatis.myframework.mybatis2.sqlsource;

import com.skills.mybatis.myframework.mybatis2.handler.ParameterMappingTokenHandler;
import com.skills.mybatis.myframework.mybatis2.sqlnode.DynamicContext;
import com.skills.mybatis.myframework.mybatis2.sqlnode.MixedSqlNode;
import com.skills.mybatis.myframework.mybatis2.utils.GenericTokenParser;

/**
 * describe:分装最多只带有#{}的sql信息
 * #{}需要被解析一次，利用占位符“？”进行替代，这样就不必重复解析，
 * ${}每一次调用时，就需要去解析一次Sql语句
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class RawSqlSource implements SqlSource {
    private StaticSqlSource staticSqlSource;
    public RawSqlSource(MixedSqlNode mixedSqlnode) {
        //#{}处理的时候，不需要入参对象的支持
        DynamicContext context = new DynamicContext(null);
        //处理sqlnode
        mixedSqlnode.apply(context);


        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
        //将#{}解析成？并保存参数信息
        GenericTokenParser tokenParser = new GenericTokenParser("#{","}",handler);
        //获取真正可以执行的Sql语句
        String sql = tokenParser.parse(context.getSql());

        //该sqlSource就是分装已经解析完成的sql语句
        staticSqlSource = new StaticSqlSource(sql,handler.getParameterMappings());
    }


    @Override
    public BoundSql getBoundSql(Object param) {
        return staticSqlSource.getBoundSql(param);
    }
}
