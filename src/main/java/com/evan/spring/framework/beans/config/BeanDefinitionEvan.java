package com.evan.spring.framework.beans.config;

import lombok.Data;

/**
 * 用来存储配置文件中的信息
 * 相当于保存在内存中的配置
 * @author evanYang
 * @version 1.0
 * @date 2021/6/16 11:41
 */
@Data
public class BeanDefinitionEvan {

    private String beanClassName;
    private boolean lazyInit=false;
    private String factoryBeanName;
}
