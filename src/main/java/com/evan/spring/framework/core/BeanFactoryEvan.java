package com.evan.spring.framework.core;

/**
 * 单例工厂的顶层设计
 * @author evanYang
 * @version 1.0
 * @date 2021/6/16 11:36
 */
public interface BeanFactoryEvan {

    /**
     * 根据beanName 从IOC容器中获得一个实例bean
     * @param beanName
     * @return
     * @throws Exception
     */
    Object getBean(String beanName) throws Exception;

    Object getBean(Class<?> beanClass) throws Exception;
}
