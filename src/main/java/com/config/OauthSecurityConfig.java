/**   
* @Title: OauthSecurityConfig.java 
* @Package com.config 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2020-05-27 
*/
package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

/** 
* @ClassName: OauthSecurityConfig 
* @Description: Oauth2 Security配置 
* @author: zhuyj
* @date: 2020-05-27 
*/
@EnableWebSecurity
public class OauthSecurityConfig  extends WebSecurityConfigurerAdapter {
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests(authorizeRequests ->
					authorizeRequests
					.antMatchers("/css/**", "/index").permitAll()
					.antMatchers("/user/**").authenticated()
			)
            .oauth2Login();
    }
	
	@Bean
	public OAuth2UserService<OAuth2UserRequest, OAuth2User> userService(){
		return new OauthUserService();
	}
}
