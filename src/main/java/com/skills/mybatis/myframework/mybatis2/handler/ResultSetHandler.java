package com.skills.mybatis.myframework.mybatis2.handler;

import com.skills.mybatis.myframework.mybatis2.config.MappedStatement;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public class ResultSetHandler {
    private ResultSet resultSet;
    private MappedStatement mappedStatement;

    public ResultSetHandler(ResultSet resultSet, MappedStatement mappedStatement) {
        this.resultSet = resultSet;
        this.mappedStatement = mappedStatement;
    }

    public List handle() throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        List<Object> resultList = new ArrayList<>();
        Class resultType = mappedStatement.getResultType();
        String columnName=null;
        Field declaredField=null;
        Object object=null;
        while(resultSet.next()){
            //遍历一次是一个User对象
            Object instance = resultType.newInstance();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnName = metaData.getColumnName(i);
                declaredField = resultType.getDeclaredField(columnName);
                object = resultSet.getObject(i);
                declaredField.setAccessible(true);
                declaredField.set(instance,object);
            }
            resultList.add(instance);
        }
        return resultList;
    }
}
