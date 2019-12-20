package com.mico.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class MongoDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insert(){
       Custom custom = new Custom(21,"dsaf",123);
       mongoTemplate.insert(custom,"wahaha");
       /*Producer producer = new Producer(21,231,342,333);
        mongoTemplate.insert(producer);*/

    }

}
