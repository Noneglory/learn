package com.skills.mybatis.myframework.mybatis2.xmlbuilder;

import com.skills.mybatis.myframework.mybatis2.config.DataSource;
import com.skills.mybatis.myframework.mybatis2.config.Configuration;
import com.skills.mybatis.myframework.mybatis2.utils.CommUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public class XMLConfigBuilder {
    public Configuration getConfiguration() {
        return configuration;
    }

    private Configuration configuration;
    private InputStream inputStream;


    public XMLConfigBuilder(InputStream inputStream)
    {
        this.configuration = new Configuration();
        this.inputStream=inputStream;
    }

    /**
     * 解析mybatis的核心配置文件configuration.xml
     */
    public void parse(){
        Element rootElement = null;
        Element environments = null;
        Element mappers=null;
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            rootElement = document.getRootElement();
            environments = rootElement.element("environments");
            mappers = rootElement.element("mappers");
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        environmentsParse(environments);
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
        xmlMapperBuilder.parse(mappers);
    }

    /**
     * 对数据源进行解析，配置数据库连接
     * @param environments
     */
    private void environmentsParse(Element environments) {
        Element environment = environments.element("environment");
        String id= environment.attribute("id").getValue();
        Element dataSourceElement = environment.element("dataSource");
        DataSource dataSource = new DataSource();

        List<Element> propertyList = dataSourceElement.elements("property");

        for(int i=0;i<propertyList.size();i++){
            Element node = propertyList.get(i);
            String name = node.attributeValue("name");
            String value= node.attributeValue("value");
            CommUtils.setObjectValue(dataSource,name,value);
        }
        configuration.setDataSource(dataSource);
    }



}
