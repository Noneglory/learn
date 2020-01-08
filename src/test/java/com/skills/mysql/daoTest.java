package com.skills.mysql;

import com.skills.entity.Table;
import com.skills.learn.LearnApplication;
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
        String sql="select * from account where id=1";
        System.out.println(tableimpl.queryStudnet(sql));
    }
    @Test
    public void insertStudent(){
        Table table = new Table("monry",22,2);
        tableimpl.testNamedParameter(table);
    }
}