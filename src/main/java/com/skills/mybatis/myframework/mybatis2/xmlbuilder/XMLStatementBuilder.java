package com.skills.mybatis.myframework.mybatis2.xmlbuilder;

import com.skills.mybatis.myframework.mybatis2.sqlnode.IfSqlNode;
import com.skills.mybatis.myframework.mybatis2.sqlnode.StaticTextSqlNode;
import com.skills.mybatis.myframework.mybatis2.sqlnode.TextSqlNode;
import com.skills.mybatis.myframework.mybatis2.sqlnode.iface.SqlNode;
import com.skills.mybatis.myframework.mybatis2.sqlnode.MixedSqlNode;
import com.skills.mybatis.myframework.mybatis2.sqlsource.DynamicSqlSource;
import com.skills.mybatis.myframework.mybatis2.sqlsource.RawSqlSource;

import com.skills.mybatis.myframework.mybatis2.config.Configuration;
import com.skills.mybatis.myframework.mybatis2.sqlsource.SqlSource;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public class XMLStatementBuilder {
    private Configuration configuration;
    private boolean isDynamic=false;
    public XMLStatementBuilder(Configuration configuration) {
        this.configuration = configuration;
    }
    public SqlSource parse(Element statementElement) {
        //1.解析出来所有的SqlNode信息
        MixedSqlNode rootSqlnode =parseDynamicTags(statementElement);
        //isDynamic是parseDynamicTags过程中，得到的值
        //如果包含${}或者包括动态标签，则isDynamic为true
        SqlSource sqlSource=null;
        //2:将sqlNode信息，分装到SqlSource中，并且要根据是否动态去选择不同的sqlSource
        //如果isDynamic为true，则说明SqlNode集合信息里面包含${}sqlnode节点信息
        if(isDynamic){
            sqlSource = new DynamicSqlSource(rootSqlnode);
        }else{
            sqlSource = new RawSqlSource(rootSqlnode);
        }
        return  sqlSource;
    }

    /**
     * 对select标签的sql脚本进行解析，得到一个MixedSqlNode
     * @param statementElement
     * @return
     */
    private MixedSqlNode parseDynamicTags(Element statementElement) {
        List<SqlNode> sqlNodes = new ArrayList<>();
        int nodeCount=statementElement.nodeCount();
        //获取select标签的子标签，这里利用list的有序性对sql语句进行组装
        for(int i =0;i<nodeCount;i++){
            Node node =statementElement.node(i);
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


}
