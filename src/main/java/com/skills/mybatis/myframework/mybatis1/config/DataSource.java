package com.skills.mybatis.myframework.mybatis1.config;

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

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,username,password);
        return  connection;
    }

}
