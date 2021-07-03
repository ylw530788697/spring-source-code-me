package com.evan.spring.framework.beans.support;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.InputStream;
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

}
