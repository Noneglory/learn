package com.mico.mysql;

import com.mico.entity.Table;
import com.mico.learn.LearnApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearnApplication.class)
public class daoTest {
    @Autowired
    private TableImpl tableimpl;
    @Test
    public void queryStudnet() {
        String sql="select * from mytable where id=3";
        System.out.println(tableimpl.queryStudnet(sql));
    }
    @Test
    public void insertStudent(){
        Table table = new Table("monry",22,2);
        tableimpl.testNamedParameter(table);
    }
}