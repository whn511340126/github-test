package com.intouch.ssm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *   提取Properties文件中的值信息
 */
public class PropertiesUtil {

    //定义Properties文件的路径
    private static final String PATH = "serviceMethods.properties";
    //针对Properties文件定义一个空的Properties对象
    private static Properties props = new Properties( );

    //使用Properties文件填充Properties对象
    static{
       //获得当前类的类加载器
       ClassLoader classLoader=PropertiesUtil.class.getClassLoader();
       //使用类加载器,加载Properties文件进入到内存中,形成IO流
        InputStream is=classLoader.getResourceAsStream(PATH);
        //使用输入流填充Properties对象
        try {
            props.load(is);
        } catch (IOException e) {
            System.out.println("填充Properties文件失败!" );
            e.printStackTrace( );
        }
    }


    //私有化构造器
    private PropertiesUtil(){}

    //定义一个静态的工具方法
    public static String getPropertyValue(String key){
        return props.getProperty(key);
    }
}
