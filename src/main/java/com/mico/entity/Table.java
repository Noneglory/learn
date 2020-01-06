package com.mico.entity;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Table implements RowMapper<Table> {
    private String name;
    private Integer money;
    private Integer id;

    public Table() {
    }

    public Table(String name, Integer money, Integer id) {
        this.name = name;
        this.money = money;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Table mapRow(ResultSet resultSet, int rowcount) throws SQLException {
        Table table = new Table();
        table.setName(resultSet.getString("name"));
        table.setMoney(resultSet.getInt("money"));
        table.setId(resultSet.getInt("id"));
        return table;
    }
}
