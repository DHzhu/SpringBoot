package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
public class StartApp implements ApplicationRunner{
	private static Logger log = LogManager.getLogger("default");

	public static void main(String[] args) {
		new SpringApplicationBuilder(StartApp.class)
			.web(WebApplicationType.NONE)
			.bannerMode(Banner.Mode.OFF)
			.run(args);
	}
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		log.info("start");
	}
}
