package com.skills.mybatis.myframework.mybatis2.handler;

import com.skills.mybatis.myframework.mybatis2.config.MappedStatement;
import com.skills.mybatis.myframework.mybatis2.sqlsource.BoundSql;
import com.skills.mybatis.myframework.mybatis2.sqlsource.ParameterMapping;
import com.skills.mybatis.myframework.mybatis2.utils.SimpleTypeRegistry;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public class ParameterHandler {
    private Statement statement;
    private MappedStatement mappedStatement;
    private BoundSql boundSql;
    private Object param;

    public ParameterHandler(Statement statement, MappedStatement mappedStatement, BoundSql boundSql, Object param) {
        this.statement = statement;
        this.mappedStatement = mappedStatement;
        this.boundSql = boundSql;
        this.param = param;
    }

    /**
     * 对查询的结果进行封装
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    public ResultSet handle() throws NoSuchFieldException, IllegalAccessException, SQLException {
        ResultSet resultSet =null;
        if (statement instanceof PreparedStatement)
        {
            //从parameterTypeClass获取入参的类型
            PreparedStatement preparedStatement =(PreparedStatement)statement;
            Class parameterType = mappedStatement.getParameterType();
            //如果入参是8中基本类型和String类型
            if (SimpleTypeRegistry.isSimpleType(parameterType)) {
                preparedStatement.setObject(1, param);
            } else if (parameterType == Map.class) {
                //TODO 如果是map类型
            } else {
                //如果入参是POJO类型(比如User类型)
                List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
                for (int i = 0; i < parameterMappings.size(); i++) {
                    ParameterMapping parameterMapping = parameterMappings.get(i);
                    //分装的#{}里面的属性名称
                    String name = parameterMapping.getName();
                    //利用反射去入参对象根据属性名称获取指定的属性值
                    Field field = parameterType.getDeclaredField(name);
                    field.setAccessible(true);
                    Object o = field.get(param);
                    //可以使用ParameterMapping里面的type对Object类型的value进行类型处理
                    //设置statement占位符中的值
                    preparedStatement.setObject(i + 1, o);
                }
            }
            resultSet = preparedStatement.executeQuery();
        }else{
            resultSet=statement.executeQuery(boundSql.getSql());
        }
        return resultSet;
    }
}