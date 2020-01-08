package com.skills.mybatis.myframework.mybatis2.sqlnode;

import com.skills.mybatis.myframework.mybatis2.sqlnode.iface.SqlNode;

import java.util.List;

/**
 * describe: 每一个<if></if> <where></where>标签都对一个的sqlnode，在mixedSqlnode这里进行整合，这个mixedSqlNode就是一个CRUD标签对应的sqlnode
 *
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class MixedSqlNode implements SqlNode {
    private List<SqlNode> sqlNodes;
    public MixedSqlNode(List<SqlNode> sqlNodes) {
        this.sqlNodes=sqlNodes;
    }

    /**
     * 对外提供分装数据的操作
     * @param context
     */
    @Override
    public void apply(DynamicContext context) {
        for (int i = 0; i < sqlNodes.size(); i++) {
            sqlNodes.get(i).apply(context);
        }
    }
}
