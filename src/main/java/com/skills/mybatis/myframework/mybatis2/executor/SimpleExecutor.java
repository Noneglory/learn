package com.skills.mybatis.myframework.mybatis2.executor;

import com.skills.mybatis.myframework.mybatis2.config.Configuration;
import com.skills.mybatis.myframework.mybatis2.config.MappedStatement;
import com.skills.mybatis.myframework.mybatis2.handler.ParameterHandler;
import com.skills.mybatis.myframework.mybatis2.handler.ResultSetHandler;
import com.skills.mybatis.myframework.mybatis2.handler.StatementHandler;
import com.skills.mybatis.myframework.mybatis2.sqlsource.BoundSql;
import com.skills.mybatis.myframework.mybatis2.sqlsource.SqlSource;

import java.sql.*;
import java.util.List;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public class SimpleExecutor extends BaseExecutor {
    @Override
    public Object doQuery(Configuration configuration, MappedStatement mappedStatement, Object param) {
        // 1、获取Connection
        // 此处使用DataSource去优化connection的获取
        // DataSource是通过XML配置来产生的
        // XML信息是通过Configuration对象保存
        Connection connection = configuration.getDataSource().getConnection();

        // 2、获取可以JDBC执行的SQL语句
        // 【SQL信息】是配置在映射文件中的，是通过【select等statement标签】来配置的
        // 不同脚本的【SQL信息】，是封装到不同类型的【SqlNode】对象中
        // 而【SqlNode】信息是保存到【SqlSource】中的
        // 【SqlSource】的信息是封装到【MappedStatement】对象中
        // 【MappedStatement】对象被保存到【Configuration】对象中的
//        MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
//        System.out.println(mappedStatement);
        //获取sqlSource
        SqlSource sqlSource = mappedStatement.getSqlSource();
        //获取BoundSql
        BoundSql boundSql = sqlSource.getBoundSql(param);
        //获取可执行的sql语句
        String sql = boundSql.getSql();

        //获取statement的类型
        String statementType = mappedStatement.getStatementType();
        return  coreExecute(mappedStatement, param, connection, boundSql, sql, statementType);
    }

    private List coreExecute(MappedStatement mappedStatement, Object param, Connection connection, BoundSql boundSql, String sql, String statementType) {
        List resultList=null;
        try {
            StatementHandler statementHandler = new StatementHandler(connection,statementType,sql);
            Statement statement = statementHandler.getStatment();
            ParameterHandler parameterHandler = new ParameterHandler(statement,mappedStatement,boundSql,param);
            ResultSet resultSet = parameterHandler.handle();
            ResultSetHandler resultSetHandler = new ResultSetHandler(resultSet,mappedStatement);
            resultList = resultSetHandler.handle();

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
