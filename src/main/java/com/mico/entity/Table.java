package com.mico.entity;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Table implements RowMapper<Table> {
    private String name;
    private Integer age;
    private Integer id;

    public Table() {
    }

    public Table(String name, Integer age, Integer id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Table{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
        table.setAge(resultSet.getInt("age"));
        table.setId(resultSet.getInt("id"));
        return table;
    }
}
