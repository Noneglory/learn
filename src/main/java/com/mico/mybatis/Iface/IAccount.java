package com.mico.mybatis.Iface;


import com.mico.mybatis.pojo.Account;
import com.mico.mybatis.pojo.User;
import com.mico.mybatis.pojo.UserList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * describe: 纯mybatis开发
 *
 * @author leijiang
 * @date 2020/01/03
 */
public interface IAccount {
    void accountInsert(Account account);
    Account getAccountById(int id);
    UserList getUserAccount(int id);
    UserList getUserListById(int id);
    void batchUserDelete(List<Integer> uidList);
    void batchUserInsert(List<User> userList);
    void UserUpdateByUser(User user);
    void accountAddmoney(@Param(value = "account") Account account, @Param(value = "money") int money);
    void accountSubmoney(@Param(value="account") Account account,@Param(value = "money") int money);


}
