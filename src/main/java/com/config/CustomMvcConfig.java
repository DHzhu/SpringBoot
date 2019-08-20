package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.interceptor.CustomInterceptor;

@Configuration
public class CustomMvcConfig implements WebMvcConfigurer {
	
	@Autowired
	private CustomerProperties properties;
	
	@Autowired
	private CustomInterceptor customInterceptor;
	
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("redirect:" + properties.getIndex());
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(customInterceptor).addPathPatterns("/*").excludePathPatterns("/start");
	}

}
