package com.skills.mybatis.myframework.mybatis1;

import com.skills.mybatis.myframework.mybatis1.config.Configuration;
import com.skills.mybatis.myframework.mybatis1.config.MappedStatement;
import com.skills.mybatis.myframework.mybatis1.po.User;
import com.skills.mybatis.myframework.mybatis1.sqlsource.BoundSql;
import com.skills.mybatis.myframework.mybatis1.sqlsource.ParameterMapping;
import com.skills.mybatis.myframework.mybatis1.sqlsource.SqlSource;
import com.skills.mybatis.myframework.mybatis1.utils.SimpleTypeRegistry;
import com.skills.mybatis.myframework.mybatis1.xmlparse.XmlParseHelper;
import org.dom4j.DocumentException;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class Entriance {

    public List<Object> queryByJdbc(String path, String statementId, Object param) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException, DocumentException {
        XmlParseHelper xmlParseHelper = new XmlParseHelper(path);
        xmlParseHelper.configurationElement();
        Configuration configuration = xmlParseHelper.getConfiguration();
        // 1、获取Connection
        // 此处使用DataSource去优化connection的获取
        // DataSource是通过XML配置来产生的
        // XML信息是通过Configuration对象保存
        Connection connection = configuration.getDataSource().getConnection();

        // 2、获取可以JDBC执行的SQL语句
        // 【SQL信息】是配置在映射文件中的，是通过【select等statement标签】来配置的
        // 不同脚本的【SQL信息】，是封装到不同类型的【SqlNode】对象中
        // 而【SqlNode】信息是保存到【SqlSource】中的
        // 【SqlSource】的信息是封装到【MappedStatement】对象中
        // 【MappedStatement】对象被保存到【Configuration】对象中的
               MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        System.out.println(mappedStatement);
        //获取sqlSource
        SqlSource sqlSource = mappedStatement.getSqlSource();
        //获取BoundSql
        BoundSql boundSql = sqlSource.getBoundSql(param);
        //获取可执行的sql语句
        String sql = boundSql.getSql();

        //获取statement的类型
        String statementType = mappedStatement.getStatementType();
        if("prepared".equals(statementType))
        {
            //3:获取statment，由三种,statment，preparedstatment，callabeStatement
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            //4:设置参数
            handleParameter(preparedStatement,mappedStatement,boundSql,param);
            //5：执行sql语句
            ResultSet resultSet = preparedStatement.executeQuery();

            //6:处理结果集
            return  handleResultSet(resultSet,mappedStatement);
        }

        return null;
    }

    /**
     * 对查询结果进行映射处理
     * @param resultSet
     * @param mappedStatement
     */
    private List handleResultSet(ResultSet resultSet, MappedStatement mappedStatement) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        List<Object> resultList = new ArrayList<>();
        Class resultType = mappedStatement.getResultType();
        String columnName=null;
        Field declaredField=null;
        Object object=null;
        while(resultSet.next()){
            //遍历一次是一个User对象
            Object instance = resultType.newInstance();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnName = metaData.getColumnName(i);
                declaredField = resultType.getDeclaredField(columnName);
                object = resultSet.getObject(i);
                declaredField.setAccessible(true);
                declaredField.set(instance,object);
            }
            resultList.add(instance);
        }
        return resultList;
    }

    /**
     * 从mappedstatement中获取入参的类型信息，是简单类型直接setObject进行赋值，是引用类型时，从boundsql中获取到parametermappings列表(在解析sql时保证sql中？的顺序与parametmappings中这个list的顺序是一致的)，获取每个属性的名称，利用反射从
     * param对象中获取相应的值，没取到一个就绑定到preparedstatement上。完成preparedstatement的赋值。
     * @param preparedStatement
     * @param mappedStatement
     * @param boundSql
     * @param param
     * @throws SQLException
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    private void handleParameter(PreparedStatement preparedStatement, MappedStatement mappedStatement, BoundSql boundSql, Object param) throws SQLException, NoSuchFieldException, IllegalAccessException {
        //从parameterTypeClass获取入参的类型
        Class parameterType = mappedStatement.getParameterType();
        //如果入参是8中基本类型和String类型
        if(SimpleTypeRegistry.isSimpleType(parameterType)){
            preparedStatement.setObject(1,param);
        }else if(parameterType == Map.class){
            //TODO 如果是map类型
        } else{
            //如果入参是POJO类型(比如User类型)
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            for(int i =0;i<parameterMappings.size();i++){
                ParameterMapping parameterMapping = parameterMappings.get(i);
                //分装的#{}里面的属性名称
                String name  = parameterMapping.getName();
                //利用反射去入参对象根据属性名称获取指定的属性值
                Field field = parameterType.getDeclaredField(name);
                field.setAccessible(true);
                Object o = field.get(param);
                //可以使用ParameterMapping里面的type对Object类型的value进行类型处理
                //设置statement占位符中的值
                preparedStatement.setObject(i+1,o);

            }


        }
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException, IllegalAccessException, NoSuchFieldException, InstantiationException, DocumentException {
        User user = new User();
        user.setId(1);
        user.setUsername("王五");
        Entriance entriance = new Entriance();

        List<Object> test = entriance.queryByJdbc("mybatis/myframework/mybatis-config.xml", "test.findUserById", user);
        System.out.println(test);
    }

}
