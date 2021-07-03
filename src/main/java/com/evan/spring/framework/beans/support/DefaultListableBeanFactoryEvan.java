package com.evan.spring.framework.beans.support;

import com.evan.spring.framework.beans.config.BeanDefinitionEvan;
import com.evan.spring.framework.context.support.AbstractApplicationContextEvan;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/6/16 11:48
 */
public class DefaultListableBeanFactoryEvan extends AbstractApplicationContextEvan {
    //存储注册信息的BeanDefinition
    protected final Map<String, BeanDefinitionEvan> beanDefinitionEvanMap=new ConcurrentHashMap<String, BeanDefinitionEvan>();
}
