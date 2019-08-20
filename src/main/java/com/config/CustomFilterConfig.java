package com.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.filter.CustomFilter;

@Configuration
public class CustomFilterConfig {

	
	@Bean
	public FilterRegistrationBean<CustomFilter> customFilterRegist(){
		FilterRegistrationBean<CustomFilter> filterRegistration = new FilterRegistrationBean<CustomFilter>();
        filterRegistration.setFilter(new CustomFilter());
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setOrder(1);
        return filterRegistration;
	}

}
