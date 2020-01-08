package com.skills.mybatis.tx;

import com.skills.mybatis.basic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTransactionTest extends basic {

    @Autowired
    private UserTransaction userTransaction;
    @Test
    public void transfer() {
        userTransaction.transfer();
    }
}