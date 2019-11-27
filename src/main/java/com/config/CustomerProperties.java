package com.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CustomerProperties {
	
	@Value("${customer.index}")
	public String index;
	
	@Value("${customer.value}")
	public String value;

}
