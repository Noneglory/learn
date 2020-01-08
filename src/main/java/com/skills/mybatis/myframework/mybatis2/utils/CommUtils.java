package com.skills.mybatis.myframework.mybatis2.utils;

import java.lang.reflect.Field;

/**
 * describe:
 *
 * @author leijiang
 * @date 2020/01/08
 */
public class CommUtils {

    /**
     * 利用反射对对象赋值
     * @param object 待赋值的对象
     * @param name 属性的名称
     * @param value 属性的值
     */
    public static void setObjectValue(Object object,String name,String value)  {
        try {
            Class clazz = object.getClass();
            Field attributeFile = clazz.getDeclaredField(name);
            attributeFile.setAccessible(true);
            attributeFile.set(object,value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
