/**   
* @Title: SecurityConfig.java 
* @Package com.config 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2020-05-26 
*/
package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/** 
* @ClassName: SecurityConfig 
* @Description: Security配置
* @author: zhuyj
* @date: 2020-05-26 
*/

public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorizeRequests ->
				authorizeRequests
					.antMatchers("/css/**", "/index").permitAll()
					.antMatchers("/user/**").hasRole("USER")
			)
			.formLogin(formLogin ->
				formLogin
					.loginPage("/login")
					.failureUrl("/login-error")
			);
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails = User.withDefaultPasswordEncoder()
									.username("user")
									.password("password")
									.roles("USER")
									.build();
		return new InMemoryUserDetailsManager(userDetails);
	}
}
