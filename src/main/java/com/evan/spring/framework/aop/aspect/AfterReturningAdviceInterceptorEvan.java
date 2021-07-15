package com.evan.spring.framework.aop.aspect;

import com.evan.spring.framework.aop.intercept.MethodInterceptorEvan;
import com.evan.spring.framework.aop.intercept.MethodInvocationEvan;

import java.lang.reflect.Method;

/**
 * Created by Tom on 2019/4/15.
 */
public class AfterReturningAdviceInterceptorEvan extends AbstractAspectAdviceEvan implements AdviceEvan, MethodInterceptorEvan {

    private JoinPointEvan joinPoint;

    public AfterReturningAdviceInterceptorEvan(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    public Object invoke(MethodInvocationEvan mi) throws Throwable {
        Object retVal = mi.proceed();
        this.joinPoint = mi;
        this.afterReturning(retVal,mi.getMethod(),mi.getArguments(),mi.getThis());
        return retVal;
    }

    private void afterReturning(Object retVal, Method method, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.joinPoint,retVal,null);
    }

}
