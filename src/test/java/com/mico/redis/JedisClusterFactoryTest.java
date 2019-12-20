package com.mico.redis;


import com.mico.learn.LearnApplication;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisCluster;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearnApplication.class)
public class JedisClusterFactoryTest {
    @Autowired
    private JedisCluster jedisCluster;

    @Test
    public void getObject() {
    }

    @Ignore
    @Test
    public void setValue(){
        jedisCluster.set("yourn","wahaha");
    }
    @Ignore
    @Test
    public void getValue(){

        System.out.println(jedisCluster.get("yourn"));
    }
}