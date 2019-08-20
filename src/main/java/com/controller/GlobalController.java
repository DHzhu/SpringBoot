package com.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.config.CustomerProperties;

@Controller
public class GlobalController {
	
	@Autowired
	public CustomerProperties properties;
	
	private static Logger log = LogManager.getLogger("default");
	@ResponseBody
	@RequestMapping("/start")
	public Map<String, Object> queryDays(){
		log.info("start");
		return null;
	}

}
