package com.mico.mybatis.Iface;


import com.mico.mybatis.pojo.Account;
import com.mico.mybatis.pojo.User;
import com.mico.mybatis.pojo.UserList;

import java.util.List;

/**
 * describe:
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

}
