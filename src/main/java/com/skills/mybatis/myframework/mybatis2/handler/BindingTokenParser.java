package com.skills.mybatis.myframework.mybatis2.handler;

import com.skills.mybatis.myframework.mybatis2.sqlnode.DynamicContext;
import com.skills.mybatis.myframework.mybatis2.utils.OgnlUtils;
import com.skills.mybatis.myframework.mybatis2.utils.SimpleTypeRegistry;
import com.skills.mybatis.myframework.mybatis2.handler.iface.TokenHandler;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class BindingTokenParser implements TokenHandler {
    private DynamicContext context;

    public BindingTokenParser(DynamicContext context) {
        this.context = context;
    }

    /**
     * expression 比如说${username},那么expression就是username，username也就是Ognl表达式
     * @param expression
     * @return
     */
    @Override
    public String handleToken(String expression) {
        Object paramObject  = context.getBindings().get("_parameter");
        if(paramObject==null)
        {
            return  "";
        }else if(SimpleTypeRegistry.isSimpleType(paramObject.getClass())){
            return String.valueOf(paramObject);
        }

        //使用ognl api去获取相应的值
        Object value = OgnlUtils.getValue(expression,paramObject);
        String strValue=value==null?"":String.valueOf(value);
        return strValue;

    }
}
