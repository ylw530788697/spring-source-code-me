package com.evan.spring.framework.aop.aspect;

import com.evan.spring.framework.aop.intercept.MethodInterceptorEvan;
import com.evan.spring.framework.aop.intercept.MethodInvocationEvan;

import java.lang.reflect.Method;

/**
 * Created by Tom on 2019/4/15.
 */
public class AfterThrowingAdviceInterceptorEvan extends AbstractAspectAdviceEvan implements AdviceEvan, MethodInterceptorEvan {


    private String throwingName;

    public AfterThrowingAdviceInterceptorEvan(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    public Object invoke(MethodInvocationEvan mi) throws Throwable {
        try {
            return mi.proceed();
        }catch (Throwable e){
            invokeAdviceMethod(mi,null,e.getCause());
            throw e;
        }
    }

    public void setThrowName(String throwName){
        this.throwingName = throwName;
    }
}
