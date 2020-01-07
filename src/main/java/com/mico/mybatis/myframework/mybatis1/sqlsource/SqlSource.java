package com.mico.mybatis.myframework.mybatis1.sqlsource;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/06
 */
public interface SqlSource {
    // String username = "王五";
    // statement的使用方式，也就是动态拼接SQL语句
    // "select * from user where username = "+username

    // "select * from user where username = ? AND age = ?"
    // preparedStatement.setString(1,"王五") //【王五】得去入参对象的username属性中获取
    // preparedStatement.setString(2,"男") //【男】得去入参对象的age属性中获取
    BoundSql getBoundSql(Object param);
}
