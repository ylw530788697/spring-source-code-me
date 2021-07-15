package com.evan.spring.framework.aop.intercept;

/**
 * Created by Tom on 2019/4/14.
 */
public interface MethodInterceptorEvan {
    Object invoke(MethodInvocationEvan invocation) throws Throwable;
}
