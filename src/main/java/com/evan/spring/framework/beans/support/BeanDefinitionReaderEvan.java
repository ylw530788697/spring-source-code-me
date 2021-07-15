package com.evan.spring.framework.beans.support;

import com.evan.spring.design.decorator.Battercake;
import com.evan.spring.framework.beans.config.BeanDefinitionEvan;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/6/16 12:06
 */
public class BeanDefinitionReaderEvan {
    private List<String> regisBeanClasses= new ArrayList<String>();
    private Properties config=new Properties();
    //固定配置文件中的key，相对于xml的规范
    private final String SCAN_PACKAGE="scanPackage";

    public BeanDefinitionReaderEvan(String...locations){
        //通过RUL定位找到其所对应的文件，然后转换为文件流
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""));
        try {
            config.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doScanner(String scanPackage){
        //转换为文件路径，实际上就是把.替换为/就ok了
        URL url = this.getClass().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            }else{
                if (!file.getName().endsWith(".class")){
                    continue;
                }
                String className=(scanPackage+"."+file.getName().replace(".class",""));
                regisBeanClasses.add(className);
            }
        }
    }

    public Properties getConfig(){
        return this.config;
    }

    /**
     * 把配置文件转换成BeanDefinitionReaderEvan 对象，以便之后IOC操作方便
     * @return
     */
    public List<BeanDefinitionEvan> loadBeanDefinitions(){
        List<BeanDefinitionEvan> result = new ArrayList<BeanDefinitionEvan>();
        try {
            for (String className : regisBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                //如果是一个接口，是不能实例化的 用它的类来实例化
                if (beanClass.isInterface()){
                    continue;
                }
                //beanName有三种情况
                //1，默认是类名首字母小写
                //2，自定义名字
                //3，接口注入
                String factoryBeanName = toLowerFirstCase(beanClass.getSimpleName());
                BeanDefinitionEvan beanDefinitionEvan = doCreateBeanDefinition(factoryBeanName, beanClass.getName());
                result.add(beanDefinitionEvan);

                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> anInterface : interfaces) {
                    //如果多个实现类，只能覆盖
                    //why？  因为spring 没那么智能，就是这么傻
                    //这个时候，可以自定义名字
                    BeanDefinitionEvan beanDefinitionEvan1 = doCreateBeanDefinition(anInterface.getName(), beanClass.getName());
                    result.add(beanDefinitionEvan1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 把每一个配置信息解析成一个BeanDefinition
     * @param factoryBeanName
     * @param beanClassName
     * @return
     */
    private BeanDefinitionEvan doCreateBeanDefinition(String factoryBeanName,String beanClassName){
        BeanDefinitionEvan beanDefinition = new BeanDefinitionEvan();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    /**
     * 第一个字母转化成小写
     * @param simpleName
     * @return
     */
    private String toLowerFirstCase(String simpleName){
        char[] chars = simpleName.toCharArray();
        // 在java中，对char做算学运算，实际上就是对ASCII码做算学运算
        chars[0]+=32;
        return String.valueOf(chars);
    }

}
