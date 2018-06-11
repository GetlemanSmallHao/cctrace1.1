package com.cctrace.socketServers.delData;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

// 锟轿匡拷锟斤拷http://www.iteye.com/problems/85363
public class SpringFactory implements BeanFactoryAware {

	private static BeanFactory beanFactory;
	
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory=beanFactory;
	}
	
	public static <O> O getBean(String beanName){
		return (O)beanFactory.getBean(beanName);
	}
	
}
