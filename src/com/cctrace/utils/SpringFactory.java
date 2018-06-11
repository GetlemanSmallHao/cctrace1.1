package com.cctrace.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class SpringFactory implements BeanFactoryAware {

	private static BeanFactory beanFactory;
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
	public static <T> T getBean(String beanName){
		return (T)beanFactory.getBean(beanName);
	}
}
