package com.skills;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTEst {
    private static String url="jdbc:mysql://127.0.0.1:3306/user?useUnicode=true&autoReconnection=true&zeroDateTimeBehavior=round&serverTimezone=UTC";
    private static String user="root";
    private static String password="Admin123";
    private  static String driver="com.mysql.cj.jdbc.Driver";
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  Connection getConnecion() throws SQLException {
        Connection connection= DriverManager.getConnection(url,user,password);
        System.out.println(connection);
        return  connection;
    }

    public static void main(String[] args) throws SQLException {
        JdbcTEst jdbcTest = new JdbcTEst();
        jdbcTest.getConnecion();
    }
}
