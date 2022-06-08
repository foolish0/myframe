package com.gabriel.myframe.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanInitEnhance implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("===> postProcessBeforeInitialization " + beanName + ":" + bean);
        System.out.println("postBefore" + bean.toString());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("===> postProcessAfterInitialization " + beanName + ":" + bean);
        System.out.println("postAfter" + bean.toString());
        return bean;
    }
}
