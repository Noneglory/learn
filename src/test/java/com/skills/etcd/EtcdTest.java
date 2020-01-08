package com.skills.etcd;

import com.skills.learn.LearnApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LearnApplication.class)
public class EtcdTest {
    @Autowired
    private EtcdDao etcdDao;

    @Test
    public void setKeyValueTest() throws ExecutionException, InterruptedException {
        etcdDao.setKeyValue("/name/message/top","aaaaaaaaa");
    }

    @Test
    public void getEtcdValueByKeyTest(){
        try {
            String ss = etcdDao.getEtcdValueByKey("/name/message/top");
            System.out.println(ss);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
