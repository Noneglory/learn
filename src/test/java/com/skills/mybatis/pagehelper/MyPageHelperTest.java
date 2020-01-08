package com.skills.mybatis.pagehelper;

import com.skills.mybatis.basic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MyPageHelperTest extends basic {

    @Autowired
    private MyPageHelper myPageHelper;

    @Test
    public void SelectTest(){
        myPageHelper.Select();
    }

}