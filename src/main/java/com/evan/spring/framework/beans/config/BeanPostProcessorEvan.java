package com.evan.spring.framework.beans.config;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/7/14 19:55
 */
public class BeanPostProcessorEvan {
    public Object postProcessBeforeInitialization(Object bean,String beanName) throws Exception{
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean,String beanName) throws Exception{
        return bean;
    }
}
