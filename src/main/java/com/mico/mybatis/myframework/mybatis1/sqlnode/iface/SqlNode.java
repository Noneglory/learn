package com.mico.mybatis.myframework.mybatis1.sqlnode.iface;

import com.mico.mybatis.myframework.mybatis1.sqlnode.DynamicContext;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/06
 */
public interface SqlNode {
    /**
     * 解析功能，最终解析出来的sql语句，封装到DynamicContext中的Stringbuffer对象中，
     * 解析的时候，可能要用到入参对象
     *
     * 此时解析出来的sql语句，只能根据动态标签的逻辑，完成字符串的拼接，他还没有被解析
     * @param context
     */
    void apply(DynamicContext context);
}
