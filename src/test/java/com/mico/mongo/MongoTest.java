package com.mico.mongo;

import com.mico.basic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoTest extends basic {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public  void test(){
        System.out.println(mongoTemplate);
    }

}
