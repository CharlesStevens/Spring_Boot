package com.stevens.spring.annotations.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext();
		((AnnotationConfigApplicationContext) context).scan("com.stevens.annotations.beans");
		((AnnotationConfigApplicationContext) context).refresh();

		SpringBeanA bean1 = (SpringBeanA) context.getBean("springBeanA");
		bean1.setMessage("Hello Spring");

		System.out.println("Bean invocation first time : " + bean1.getMessage());

		SpringBeanC bean2 = (SpringBeanC) context.getBean("springBeanC");
		bean2.setMessage("Hello Spring BeanC");

		System.out.println("Bean invocation first time : " + bean2.getMessage());

		((AnnotationConfigApplicationContext) context).registerShutdownHook();
		((AnnotationConfigApplicationContext) context).close();
	}
}
