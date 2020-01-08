package com.skills.mybatis.tx;

import com.skills.mybatis.Iface.UserDao;
import com.skills.mybatis.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/07
 */
@Service
public class UserTransaction {
    @Autowired
    private UserDao userDao;

    @Transactional
    public void transfer(){
        Account outaccount = new Account();
        outaccount.setId(1);
        userDao.updateaccountSubmoney(outaccount,200);
//        if(1==1){
//                throw new RuntimeException("状态错误");
//        }
        Account inaccount = new Account();
        inaccount.setId(2);
        userDao.updateaccountAddmoney(inaccount,200);

    }
}
