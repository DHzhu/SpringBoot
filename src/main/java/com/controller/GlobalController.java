package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import utils.LogUtil;



@Controller
public class GlobalController {
	@ResponseBody
	@RequestMapping("/start")
	public Map<String, Object> queryDays(){
		Logger loggerXml = LogUtil.getLoggerByXml("infolog");
		Logger logger = LogUtil.getLogger("test");
		loggerXml.info("==========================");
		logger.info("test");
		LogUtil.stop("test");
		Map<String, Object> map = new HashMap<>();
		map.put("result", "success");
		return map;
	}
}
