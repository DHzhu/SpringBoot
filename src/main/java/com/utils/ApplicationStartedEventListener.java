/**   
* @Title: ApplicationStartedEventListener.java 
* @Package com.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2020-05-22 
*/
package com.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/** 
* @ClassName: ApplicationStartedEventListener 
* @Description: 启动获取配置参数
* @author: zhuyj
*/
public class ApplicationStartedEventListener implements GenericApplicationListener{
	
	private static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 10;
	 
    private static Class<?>[] EVENT_TYPES = {
    						ApplicationStartingEvent.class,
    						ApplicationEnvironmentPreparedEvent.class, 
    						ApplicationPreparedEvent.class,
    						ContextClosedEvent.class, 
    						ApplicationFailedEvent.class};
 
    private static Class<?>[] SOURCE_TYPES = {SpringApplication.class, ApplicationContext.class};

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// TODO Auto-generated method stub
		if (event instanceof ApplicationEnvironmentPreparedEvent) {

			ConfigurableEnvironment envi = ((ApplicationEnvironmentPreparedEvent) event).getEnvironment();
			MutablePropertySources mps = envi.getPropertySources();
			
			List<PropertySource<?>> applicationConfig = mps.stream()
									.filter(p -> p instanceof OriginTrackedMapPropertySource && p.getName().contains("applicationConfig"))
									.collect(Collectors.toList());
			
			for(PropertySource<?> ps : applicationConfig) {
				if (ps != null) {
					if(ps.containsProperty("customer.logDir")) {
						String logDir = (String) ps.getProperty("customer.logDir");
						MDC.put("logDir", logDir);
					}
					
					if(ps.containsProperty("customer.logLevel")) {
						String logDir = (String) ps.getProperty("customer.logLevel");
						MDC.put("logLevel", logDir);
					}
				}
			}
			
			

		}

	}

	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return DEFAULT_ORDER;
	}

	@Override
	public boolean supportsEventType(ResolvableType eventType) {
		// TODO Auto-generated method stub
		return isAssignableFrom(eventType.getRawClass(), EVENT_TYPES);
	}

	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		// TODO Auto-generated method stub
		return isAssignableFrom(sourceType, SOURCE_TYPES);
	}
	
	private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
		if (type != null) {
			for (Class<?> supportedType : supportedTypes) {
				if (supportedType.isAssignableFrom(type)) {
					return true;
				}
			}
		}
		return false;
	}

}
