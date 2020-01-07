package com.mico.mybatis.Iface;

import com.mico.mybatis.pojo.Account;
import com.mico.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/*
 *
 * mybatis集成spring，以及c3p0,主句库主从结构，动态路由测试。
 *
 * @author leijiang
 * @date 2020/01/04
 */
@Component
public interface UserDao {
    /**
     * 列出所有用户
     * @return
     */
    List<User> queryUser();
    void updateaccountAddmoney(@Param(value = "account") Account account, @Param(value = "money") int money);
    void updateaccountSubmoney(@Param(value="account") Account account,@Param(value = "money") int money);
}
