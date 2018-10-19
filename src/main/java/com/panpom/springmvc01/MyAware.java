package com.panpom.springmvc01;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取系统applicationContext  拿到系统的bean注册bean的操作
 */
public class MyAware implements ApplicationContextAware {
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            System.out.println("MyAware====="+name);
        }
    }
}
