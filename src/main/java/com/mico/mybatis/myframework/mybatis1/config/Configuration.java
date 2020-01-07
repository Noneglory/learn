package com.mico.mybatis.myframework.mybatis1.config;



import java.util.HashMap;
import java.util.Map;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class Configuration {
    private DataSource dataSource;
    private Map<String,MappedStatement> mappedStatementMap = new HashMap();

    public Configuration() {
    }

    public Configuration(DataSource dataSource, Map<String, MappedStatement> mappedStatementMap) {
        this.dataSource = dataSource;
        this.mappedStatementMap = mappedStatementMap;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addMappedStatement(String id,MappedStatement mappedStatement){
        mappedStatementMap.put(id,mappedStatement);
    }

    public MappedStatement getMappedStatement(String id)
    {
        return  mappedStatementMap.get(id);
    }
}
