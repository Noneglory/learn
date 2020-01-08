package com.skills.mybatis.myframework.mybatis2.factory;

import com.skills.mybatis.myframework.mybatis2.config.Configuration;
import com.skills.mybatis.myframework.mybatis2.factory.iface.SqlSessionFactory;
import com.skills.mybatis.myframework.mybatis2.xmlbuilder.XMLConfigBuilder;

import java.io.InputStream;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public class SqlSessionFactoryBuilder {
    private Configuration configuration;
    private InputStream inputStream;
    public SqlSessionFactoryBuilder(InputStream inputStream) {
        this.inputStream =inputStream;
    }

    public SqlSessionFactory build() {
        XMLConfigBuilder configBuilder = new XMLConfigBuilder(inputStream);
        configBuilder.parse();
        configuration = configBuilder.getConfiguration();
        return new DefaultSqlSessionFactory(configuration);
    }
}
