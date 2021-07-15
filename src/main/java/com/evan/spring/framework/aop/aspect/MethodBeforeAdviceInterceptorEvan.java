package com.evan.spring.framework.aop.aspect;

import com.evan.spring.framework.aop.intercept.MethodInterceptorEvan;
import com.evan.spring.framework.aop.intercept.MethodInvocationEvan;

import java.lang.reflect.Method;

/**
 * Created by Tom on 2019/4/15.
 */
public class MethodBeforeAdviceInterceptorEvan extends AbstractAspectAdviceEvan implements AdviceEvan, MethodInterceptorEvan {


    private JoinPointEvan joinPoint;
    public MethodBeforeAdviceInterceptorEvan(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    private void before(Method method,Object[] args,Object target) throws Throwable{
        //传送了给织入参数
        //method.invoke(target);
        super.invokeAdviceMethod(this.joinPoint,null,null);

    }
    
    public Object invoke(MethodInvocationEvan mi) throws Throwable {
        //从被织入的代码中才能拿到，JoinPoint
        this.joinPoint = mi;
        before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }
}
