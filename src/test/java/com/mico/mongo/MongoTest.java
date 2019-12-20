package com.mico.mongo;

import com.mico.basic;
import com.mico.learn.LearnApplication;
import com.mico.mongodb.MongoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearnApplication.class)
public class MongoTest{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoDao mongoDao;
    @Test
    public  void test(){
        System.out.println(mongoTemplate);
    }

    @Test
    public void testInsert(){
        mongoDao.insert();
    }
}
