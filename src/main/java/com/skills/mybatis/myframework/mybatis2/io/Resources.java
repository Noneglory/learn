package com.skills.mybatis.myframework.mybatis2.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * describe: 将文件转为字符流或者字节流
 *
 * @author leijiang
 * @date 2020/01/08
 */
public class Resources {
    public static InputStream getResourcesAsStream(String path){
        return  Resources.class.getClassLoader().getResourceAsStream(path);
    }
    public static Reader getResourcesAsReader(String path)
    {
        InputStream inputStream = getResourcesAsStream(path);
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader;
    }
}
