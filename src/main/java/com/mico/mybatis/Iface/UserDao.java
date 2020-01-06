package com.mico.mybatis.Iface;

import com.mico.mybatis.pojo.User;

import java.util.List;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/04
 */
public interface UserDao {
    /**
     * 列出所有用户
     * @return
     */
    List<User> queryUser();
}
