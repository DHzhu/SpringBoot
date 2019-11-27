package com.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.config.CustomerProperties;
import com.utils.Result;

@Controller
@SuppressWarnings("rawtypes")
public class GlobalController {
	
	@Autowired
	public CustomerProperties properties;
	
	@Value("${testValue:local}")
	private String apolloValue;
	
	private static Logger log = LogManager.getLogger("default");
	
	@ResponseBody
	@RequestMapping("/start")
	public Result queryDays(){
		log.info("start");
		return Result.SUCESS(apolloValue).setData(properties.getValue());
	}

}
