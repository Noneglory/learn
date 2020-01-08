package com.skills.mybatis.myframework.mybatis2.factory.iface;



import java.util.List;

/**
 * describe: 提供给外部的接口
 *
 * @author leijiang
 * @date 2020/01/08
 */
public interface SqlSession {
        <T> List<T> selectList(String statementId,Object param);
}
