package com.skills.mybatis.myframework.mybatis2.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class DataSource {
    private String driver;
    private String username;
    private String password;
    private String url;

    public DataSource() {
    }

    public DataSource(String driver, String username, String password, String url) {
        this.driver = driver;
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public Connection getConnection()  {
        Connection connection=null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }

}
