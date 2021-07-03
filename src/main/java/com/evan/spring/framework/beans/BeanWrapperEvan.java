package com.evan.spring.framework.beans;

import lombok.Data;

/**
 * @author evanYang
 * @version 1.0
 * @date 2021/6/16 11:44
 */
@Data
public class BeanWrapperEvan {
    private Object wrappedInstance;
    private Class<?> wrappedClass;
}
