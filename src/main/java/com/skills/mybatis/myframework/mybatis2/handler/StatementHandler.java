package com.skills.mybatis.myframework.mybatis2.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public class StatementHandler {
    private Connection connection;
    private String statementType;
    private String sql;

    public StatementHandler(Connection connection,String statmentType,String sql) {
        this.connection = connection;
        this.statementType=statmentType;
        this.sql=sql;
    }

    public Statement getStatment(){
        Statement  statement=null;
        try {
            if("prepared".equals(statementType)){
                statement = connection.prepareStatement(sql);
            }else if("simple".equals(statementType)) {
                statement = connection.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  statement;
    }
}
