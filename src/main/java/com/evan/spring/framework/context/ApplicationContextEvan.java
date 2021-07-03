package com.evan.spring.framework.context;

import com.evan.spring.framework.beans.BeanWrapperEvan;
import com.evan.spring.framework.beans.support.BeanDefinitionReaderEvan;
import com.evan.spring.framework.beans.support.DefaultListableBeanFactoryEvan;
import com.evan.spring.framework.core.BeanFactoryEvan;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/6/16 12:00
 */
public class ApplicationContextEvan extends DefaultListableBeanFactoryEvan implements BeanFactoryEvan {
    private String[] configLocations;
    private BeanDefinitionReaderEvan reader;

    //单例的Ioc容器缓存
    private Map<String,Object> singletonObjects=new ConcurrentHashMap<String, Object>();

    private Map<String, BeanWrapperEvan> beanWrapperEvanMap=new ConcurrentHashMap<String, BeanWrapperEvan>();

    public Object getBean(String beanName) throws Exception {
        return null;
    }

    public Object getBean(Class<?> beanClass) throws Exception {
        return null;
    }

    public ApplicationContextEvan(String ...configLocations) {
        this.configLocations=configLocations;
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void refresh(){
        //1，定位、定位配置文件
        reader=new BeanDefinitionReaderEvan(this.configLocations);
        //2,加载配置文件，扫描相关的类，把他们封装成BeanDefinition


    }


}
