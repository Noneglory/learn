package com.skills.mybatis.myframework.mybatis2.xmlbuilder;

import com.skills.mybatis.myframework.mybatis2.config.MappedStatement;
import com.skills.mybatis.myframework.mybatis2.sqlsource.SqlSource;
import com.skills.mybatis.myframework.mybatis2.config.Configuration;
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
public class XMLMapperBuilder {
    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(Element mappers) {
        Element mapper = mappers.element("mapper");
        String resource = mapper.attributeValue("resource");
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        SAXReader reader = new SAXReader();
        try {
            Element mapperElement = reader.read(resourceAsStream).getRootElement();
            mapperParse(mapperElement);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对mapper文件进行解析
     * @param mapperElement
     * @throws ClassNotFoundException
     */
    private void mapperParse(Element mapperElement) throws ClassNotFoundException {
        String namespace = mapperElement.attributeValue("namespace");
//        Element statementElement = mapperElement.element("select");

        List<Element> selectList = mapperElement.elements("select");
        for (Element ele:selectList) {
            StatementElementParse(ele,namespace);
        }

    }

    /**
     * 每一个CURD标签进行解析
     * @param statementElement
     * @throws ClassNotFoundException
     */
    private void StatementElementParse(Element statementElement,String namespace)  {

        String id = statementElement.attributeValue("id");
        String statementId = namespace+"."+id;

        String parameterType = statementElement.attributeValue("parameterType");
        Class parameterTypeClass = Resolve(parameterType);
        String resultType = statementElement.attributeValue("resultType");
        Class resultTypeClass = Resolve(resultType);
        String statementType = statementElement.attributeValue("statementType");


        XMLStatementBuilder xmlStatementBuilder = new XMLStatementBuilder(configuration);
        SqlSource sqlSource= xmlStatementBuilder.parse(statementElement);
        //TODO 使用构建者模式去优化
        MappedStatement mappedStatement = new MappedStatement(statementId,parameterTypeClass,resultTypeClass,statementType,sqlSource);
        configuration.addMappedStatement(statementId,mappedStatement);
    }

    private Class Resolve(String parameterType)  {

        Class clazz=null;
        try {
            clazz=Class.forName(parameterType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz;
    }
}
