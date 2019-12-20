package com.mico.mysql;

import com.mico.entity.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class TableImpl {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Table queryStudnet(String sql){
       Table table =  jdbcTemplate.queryForObject(sql,new Table());
       return  table;
    }

    public void testNamedParameter(Table table){
        String sql = "insert into mytable(name,age,id) values (:name,:age,:id)";
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(table);
        namedParameterJdbcTemplate.update(sql,sqlParameterSource);
    }
}
