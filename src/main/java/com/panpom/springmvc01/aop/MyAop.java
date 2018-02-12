package com.panpom.springmvc01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by majianghua on 2018/1/3.
 */

@Aspect
@Component
public class MyAop {

    @Pointcut("execution(public * com.panpom.springmvc01.action.*.get*(..))")
    private void aopTest() {

    }
    @Before("aopTest()")
    public void before() {
        System.out.println("调用dao方法前拦截" + new Date().getTime());
    }
    @After("aopTest()" + "&&args(name)")
    public void after(String name) {
        System.out.println(name);
        System.out.println("调用dao方法之后拦截" + new Date().getTime());
    }
    @Around("aopTest()")
    public void around(ProceedingJoinPoint pdj) {
        System.out.println("调用dao之前的环绕拦截" + new Date().getTime());
        try {
            pdj.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("调用dao之后的环绕拦截" + new Date().getTime());
    }


}
