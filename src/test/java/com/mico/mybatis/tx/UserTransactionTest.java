package com.mico.mybatis.tx;

import com.mico.mybatis.basic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserTransactionTest extends basic {

    @Autowired
    private UserTransaction userTransaction;
    @Test
    public void transfer() {
        userTransaction.transfer();
    }
}