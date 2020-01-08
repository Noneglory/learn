package com.skills.mybatis.myframework.mybatis2.handler;

import com.skills.mybatis.myframework.mybatis2.sqlsource.ParameterMapping;
import com.skills.mybatis.myframework.mybatis2.handler.iface.TokenHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/06
 */
public class ParameterMappingTokenHandler implements TokenHandler {
    private List<ParameterMapping> parameterMappings=new ArrayList<>();
    // content是参数名称
    // content 就是#{}中的内容

    public String handleToken(String content) {
        parameterMappings.add(buildParameterMapping(content));
        return "?";
    }

    private ParameterMapping buildParameterMapping(String content) {
        ParameterMapping parameterMapping = new ParameterMapping(content);
        return parameterMapping;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
