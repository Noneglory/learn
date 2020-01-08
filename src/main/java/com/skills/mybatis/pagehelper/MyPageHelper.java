package com.skills.mybatis.pagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.skills.mybatis.Iface.UserDao;
import com.skills.mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * describe: 基于mybatis与spring来实现分页
 *
 * @author leijiang
 * @date 2020/01/07
 */
@Component
public class  MyPageHelper{

    @Autowired
    private UserDao userDao;

    public void Select(){
        PageHelper.startPage(2,4);
        List<User> users = userDao.queryUser();
        PageInfo<User> pageInfo=new PageInfo(users);
        for(User user:users){
            System.out.println(user);
        }
        System.out.println(users);
    }

}
