package com.mico.mybatis.pagehelper;

import com.mico.mybatis.basic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class MyPageHelperTest extends basic {

    @Autowired
    private MyPageHelper myPageHelper;

    @Test
    public void SelectTest(){
        myPageHelper.Select();
    }

}