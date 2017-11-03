/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.alibaba.dubbo.common;

import org.junit.Test;
import java.net.URL;
import java.util.Enumeration;

/**
 * Date 17/11/1 下午2:15
 * Author lijinlong02@baidu.com
 */
public class MuseTest {

    @Test
    public void testClass() throws Exception{
        String clazzName = URLTest.class.getName(); // com.alibaba.dubbo.common.URLTest
        Class clazz = Class.forName("com.alibaba.dubbo.common.URLTest");
        ClassLoader clazzLoader = clazz.getClassLoader();
        // file或者path="/Users/lijinlong02/alibaba/dubbo/dubbo-common/target/test-classes/com/alibaba/dubbo/common/URLTest.class"
        URL url = clazzLoader.getResource("com/alibaba/dubbo/common/URLTest.class");
        // Enumeration<URL> urls = clazzLoader.getResources("com/alibaba/dubbo/common/URLTest.class");
        Enumeration<URL> urls = clazzLoader.getResources("com/alibaba/dubbo/common/URL.class");
        while (urls.hasMoreElements()) {
            URL url1 = urls.nextElement();
            System.out.println(url1.getFile());
        }
    }
}
