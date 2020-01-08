package com.mico.mybatis.myframework.mybatis1.xmlparse;

import com.mico.mybatis.myframework.mybatis1.config.Configuration;
import com.mico.mybatis.myframework.mybatis1.config.DataSource;
import com.mico.mybatis.myframework.mybatis1.config.MappedStatement;
import com.mico.mybatis.myframework.mybatis1.sqlnode.IfSqlNode;
import com.mico.mybatis.myframework.mybatis1.sqlnode.MixedSqlNode;
import com.mico.mybatis.myframework.mybatis1.sqlnode.StaticTextSqlNode;
import com.mico.mybatis.myframework.mybatis1.sqlnode.TextSqlNode;
import com.mico.mybatis.myframework.mybatis1.sqlnode.iface.SqlNode;
import com.mico.mybatis.myframework.mybatis1.sqlsource.DynamicSqlSource;
import com.mico.mybatis.myframework.mybatis1.sqlsource.RawSqlSource;
import com.mico.mybatis.myframework.mybatis1.sqlsource.SqlSource;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class XmlParseHelper {
    public String path;

    public XmlParseHelper(String path) {
        this.path = path;
    }
    Configuration configuration= new Configuration();
    private boolean isDynamic;
    /**
     * 解析xml文件，第一步
     * @return
     * @throws DocumentException
     */
    public void configurationElement() throws DocumentException, NoSuchFieldException, IllegalAccessException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        Element rootElement = document.getRootElement();
        rootElementParse(rootElement);
    }

    /**
     * 解析xml文件中的configuration标签
     * @param rootElement
     * @return
     */
    private void rootElementParse(Element rootElement) throws NoSuchFieldException, IllegalAccessException {
        Element environments = rootElement.element("environments");
        environmentsParse(environments);
        Element mappers = rootElement.element("mappers");
        mappersPrase(mappers);
    }

    /**
     * 解析environment标签,配置数据源
     * @param environments
     */
    private void environmentsParse(Element environments) throws NoSuchFieldException, IllegalAccessException {
        Element environment = environments.element("environment");
        String id= environment.attribute("id").getValue();
        Element dataSourceElement = environment.element("dataSource");
        DataSource dataSource = new DataSource();

        List<Element> propertyList = dataSourceElement.elements("property");

        for(int i=0;i<propertyList.size();i++){
            Element node = propertyList.get(i);
            String name = node.attributeValue("name");
            String value= node.attributeValue("value");
            Class clazz = dataSource.getClass();
            Field attributeFile = clazz.getDeclaredField(name);
            attributeFile.setAccessible(true);
            attributeFile.set(dataSource,value);
        }
        configuration.setDataSource(dataSource);
    }

    private void mappersPrase(Element mappers) {
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
     * 解析mapper中的select标签
     * @param mapperElement
     */
    private void mapperParse(Element mapperElement) throws ClassNotFoundException {
        String namespace = mapperElement.attributeValue("namespace");
        Element selectElement = mapperElement.element("select");

        String id = selectElement.attributeValue("id");
        String statementId = namespace+"."+id;

        String parameterType = selectElement.attributeValue("parameterType");
        Class parameterTypeClass = Class.forName(parameterType);
        String resultType = selectElement.attributeValue("resultType");
        Class resultTypeClass = Class.forName(resultType);
        String statementType = selectElement.attributeValue("statementType");


        SqlSource sqlSource = createSqlSource(selectElement);
        //TODO 使用构建者模式去优化
        MappedStatement mappedStatement = new MappedStatement(statementId,parameterTypeClass,resultTypeClass,statementType,sqlSource);
        configuration.addMappedStatement(statementId,mappedStatement);
    }

    private SqlSource createSqlSource(Element selectElement) {
        //1.解析出来所有的SqlNode信息
        MixedSqlNode rootSqlnode =parseDynamicTags(selectElement);
        //isDynamic是parseDynamicTags过程中，得到的值
        //如果包含${}或者包括动态标签，则isDynamic为true
        SqlSource sqlSource;
        //2:将sqlNode信息，分装到SqlSource中，并且要根据是否动态去选择不同的sqlSource
        //如果isDynamic为true，则说明SqlNode集合信息里面包含${}sqlnode节点信息
        if(isDynamic){
            sqlSource = new DynamicSqlSource(rootSqlnode);
        }else{
            sqlSource = new RawSqlSource(rootSqlnode);
        }
        return  sqlSource;
    }

    private MixedSqlNode parseDynamicTags(Element selectElement) {
        List<SqlNode> sqlNodes = new ArrayList<>();
        int nodeCount=selectElement.nodeCount();
        //获取select标签的子标签，这里利用list的有序性对sql语句进行组装
        for(int i =0;i<nodeCount;i++){
            Node node =selectElement.node(i);
            if(node instanceof Text)
            {
                String sqlText = node.getText().trim();
                if(sqlText==null || "".equals(sqlText)){
                 continue;
                }

                TextSqlNode textSqlNode = new TextSqlNode(sqlText);
                if(textSqlNode.isDynamic())
                {
                    //设置是否为动态的
                    isDynamic = true;
                    sqlNodes.add(textSqlNode);
                }else{
                    sqlNodes.add(new StaticTextSqlNode(sqlText));
                }
            }else if(node instanceof Element){
                //获取动态标签的标签名称
                String nodeName = node.getName();
                //TODO设计模式优化
                if("if".equals(nodeName))
                {
                    //分装成IfSqlNode（test信息、子标签信息）
                    Element element =(Element)node;
                    //if标签的test属性信息
                    String test = element.attributeValue("test");
                    //if标签的子标签信息
                    MixedSqlNode rootSqlNode = parseDynamicTags(element);
                    //ifSqlNode就是封装if标签信息的
                    IfSqlNode ifSqlNode = new IfSqlNode(test,rootSqlNode);
                    sqlNodes.add(ifSqlNode);
                }
                isDynamic=true;
            }
        }
        MixedSqlNode mixedSqlNod =  new MixedSqlNode(sqlNodes);

        return mixedSqlNod;
    }

    public Configuration  getConfiguration(){
        return  configuration;
    }
}
