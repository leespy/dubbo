/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.config.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.alibaba.dubbo.common.Version;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ModuleConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.config.spring.ServiceBean;

/**
 * DubboNamespaceHandler
 * 
 * @author william.liangf
 * @export
 */
public class DubboNamespaceHandler extends NamespaceHandlerSupport {

    // 检查在classpath路径下，DubboNamespaceHandler.class类文件是否有重复
	static {
		Version.checkDuplicate(DubboNamespaceHandler.class);
	}

	public void init() {
        /**
         * 提供方应用信息,用于计算依赖关系
         * <dubbo:application name="hello-world-app"  />
         */
	    registerBeanDefinitionParser("application", new DubboBeanDefinitionParser(ApplicationConfig.class, true));

        /**
         * 注册中心暴露服务地址
         * <dubbo:registry address="zookeeper://10.211.55.3:2181" />
         */
        registerBeanDefinitionParser("registry", new DubboBeanDefinitionParser(RegistryConfig.class, true));

        /**
         * 用dubbo协议在20880端口暴露服务
         * <dubbo:protocol name="dubbo" port="20880" />
         */
        registerBeanDefinitionParser("protocol", new DubboBeanDefinitionParser(ProtocolConfig.class, true));

        /**
         * 声明需要暴露的服务接口(provider)，当一个接口有多种实现时，可以用group区分
         * <dubbo:service group="demo1" interface="service.DemoService" ref="demoServiceImpl1" />
         * <dubbo:service group="demo2" interface="service.DemoService" ref="demoServiceImpl2" />
         */
        registerBeanDefinitionParser("service", new DubboBeanDefinitionParser(ServiceBean.class, true)); // ServiceBean.class

        /**
         * 生成远程服务代理，可以和本地bean一样使用demoService(consumer)，消费任意一个group的服务
         * <dubbo:reference id="demoService" interface="service.DemoService" group="*"/>
         */
        registerBeanDefinitionParser("reference", new DubboBeanDefinitionParser(ReferenceBean.class, false)); // ReferenceBean.class


        registerBeanDefinitionParser("module", new DubboBeanDefinitionParser(ModuleConfig.class, true));
        registerBeanDefinitionParser("monitor", new DubboBeanDefinitionParser(MonitorConfig.class, true));
        registerBeanDefinitionParser("provider", new DubboBeanDefinitionParser(ProviderConfig.class, true));
        registerBeanDefinitionParser("consumer", new DubboBeanDefinitionParser(ConsumerConfig.class, true));
        registerBeanDefinitionParser("annotation", new DubboBeanDefinitionParser(AnnotationBean.class, true)); // AnnotationBean.class
    }

}