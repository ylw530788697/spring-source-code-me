package com.evan.spring.framework.context;

import com.evan.spring.framework.annotation.AutowiredEvan;
import com.evan.spring.framework.annotation.ControllerEvan;
import com.evan.spring.framework.annotation.ServiceEvan;
import com.evan.spring.framework.aop.config.AopConfigEvan;
import com.evan.spring.framework.aop.support.AdvisedSupportEvan;
import com.evan.spring.framework.beans.BeanWrapperEvan;
import com.evan.spring.framework.beans.config.BeanDefinitionEvan;
import com.evan.spring.framework.beans.config.BeanPostProcessorEvan;
import com.evan.spring.framework.beans.support.BeanDefinitionReaderEvan;
import com.evan.spring.framework.beans.support.DefaultListableBeanFactoryEvan;
import com.evan.spring.framework.core.BeanFactoryEvan;

import java.lang.reflect.Field;
import java.util.List;
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
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();

    //通用的ioc容器
    private Map<String, BeanWrapperEvan> factoryBeanInstanceCache = new ConcurrentHashMap<String, BeanWrapperEvan>();


    public ApplicationContextEvan(String... configLocations) {
        this.configLocations = configLocations;
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() throws Exception {
        //1，定位、定位配置文件
        reader = new BeanDefinitionReaderEvan(this.configLocations);
        //2,加载配置文件，扫描相关的类，把他们封装成BeanDefinition
        List<BeanDefinitionEvan> beanDefinitions = reader.loadBeanDefinitions();
        //3,注册 把配置信息放到容器里面（伪IOC容器）
        doRegisterBeanDefinition(beanDefinitions);
        //4,把不是延时加载的类，有提前初始化
        doAutowrited();
    }

    /**
     * 只处理非延时加载的情况
     */
    private void doAutowrited() {
        for (Map.Entry<String, BeanDefinitionEvan> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doRegisterBeanDefinition(List<BeanDefinitionEvan> beanDefinitions) throws Exception {
        for (BeanDefinitionEvan beanDefinition : beanDefinitions) {
            if (super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new Exception("The “" + beanDefinition.getFactoryBeanName() + "” is exists!!");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    /**
     * 依赖注入，从这里开始，通过读取BeanDefinition中的信息
     * 然后通过反射机制创建一个实例并返回
     * spring做法是：不会把最原始的对象放出去，会用一个BeanWrapper来进行一次包装
     * 装饰器模式：
     * 1，保留原来的OOP关系
     * 2，我需要对它进行扩展，增强（为了以后AOP打下基础）
     *
     * @param beanName
     * @return
     * @throws Exception
     */
    public Object getBean(String beanName) throws Exception {
        BeanDefinitionEvan beanDefinition = this.beanDefinitionMap.get(beanName);

        Object instance = null;
        BeanPostProcessorEvan beanPostProcessor = new BeanPostProcessorEvan();
        beanPostProcessor.postProcessBeforeInitialization(instance, beanName);

        instance = instantiateBean(beanName, beanDefinition);

        //3，把这个对象封装到BeanWrapper中
        BeanWrapperEvan beanWrapper = new BeanWrapperEvan(instance);

        //把BeanWrapper存到IOC容器里面
        //1、初始化
        //class A{ B b;}
        //class B{ A a;}
        //先有鸡还是先有蛋的问题，一个方法是搞不定的，要分两次
        //2、拿到BeanWraoper之后，把BeanWrapper保存到IOC容器中去
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);
        beanPostProcessor.postProcessAfterInitialization(instance, beanName);

        //3 注入
        populateBean(beanName, new BeanDefinitionEvan(), beanWrapper);
        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    private Object instantiateBean(String beanName, BeanDefinitionEvan beanDefinition) {
        //拿到要实例化的对象的类名
        String beanClassName = beanDefinition.getBeanClassName();
        Object instance = null;
        try {
            //beanDefinition.getFactoryBeanName()
            if (this.factoryBeanObjectCache.containsKey(beanClassName)) {
                instance = this.factoryBeanObjectCache.get(beanClassName);
            } else {
                Class<?> aClass = Class.forName(beanClassName);
                instance = aClass.newInstance();

                instantionAopConfig(beanDefinition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private void populateBean(String beanName, BeanDefinitionEvan beanDefinition, BeanWrapperEvan beanWrapper) {
        Object instance = beanWrapper.getWrappedInstance();
        Class<?> clazz = beanWrapper.getWrappedClass();

        //判断只有加了注解的类，才执行以来注入
        if (!(clazz.isAnnotationPresent(ControllerEvan.class) || clazz.isAnnotationPresent(ServiceEvan.class))) {
            return;
        }
        //获取所有的fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(AutowiredEvan.class)) {
                continue;
            }
            AutowiredEvan autowired = field.getAnnotation(AutowiredEvan.class);
            String autowireBeanName = autowired.value().trim();
            if ("".equals(autowireBeanName)) {
                autowireBeanName = field.getType().getName();
            }
            //强制访问
            field.setAccessible(true);

            try {
                if (this.factoryBeanInstanceCache.get(autowireBeanName) == null) {
                    continue;
                }
                field.set(instance, this.factoryBeanInstanceCache.get(autowireBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private AdvisedSupportEvan instantionAopConfig(BeanDefinitionEvan beanDefinition) {
        AopConfigEvan config = new AopConfigEvan();
        config.setPointCut(this.reader.getConfig().getProperty("pointCut"));
        config.setAspectClass(this.reader.getConfig().getProperty("aspectClass"));
        config.setAspectAfter(this.reader.getConfig().getProperty("aspectBefore"));
        config.setAspectAfterThrow(this.reader.getConfig().getProperty("aspectAfterThrow"));
        config.setAspectAfterThrowingName(this.reader.getConfig().getProperty("aspectAfterThrowingName"));
        return  new AdvisedSupportEvan(config);
    }


}
