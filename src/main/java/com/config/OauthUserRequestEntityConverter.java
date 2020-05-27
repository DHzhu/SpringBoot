/**   
* @Title: OAuth2UserRequestEntityConverter.java 
* @Package com.config 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2020-05-27 
*/
package com.config;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;

import java.net.URI;
import java.util.Collections;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

/** 
* @ClassName: OAuth2UserRequestEntityConverter 
* @Description: post方式请求用户信息
* @author: zhuyj
* @date: 2020-05-27 
*/
public class OauthUserRequestEntityConverter implements Converter<OAuth2UserRequest, RequestEntity<?>> {
		private static final MediaType DEFAULT_CONTENT_TYPE = MediaType.valueOf(APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		@Override
		public RequestEntity<?> convert(OAuth2UserRequest userRequest) {
			ClientRegistration clientRegistration = userRequest.getClientRegistration();

			HttpMethod httpMethod = HttpMethod.POST;

			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			URI uri = UriComponentsBuilder.fromUriString(clientRegistration.getProviderDetails().getUserInfoEndpoint().getUri())
					.build()
					.toUri();

			RequestEntity<?> request;
			headers.setContentType(DEFAULT_CONTENT_TYPE);
			MultiValueMap<String, String> formParameters = new LinkedMultiValueMap<>();
			formParameters.add(OAuth2ParameterNames.ACCESS_TOKEN, userRequest.getAccessToken().getTokenValue());
			formParameters.add(OAuth2ParameterNames.CLIENT_ID, clientRegistration.getClientId());
			
			request = new RequestEntity<>(formParameters, headers, httpMethod, uri);

			return request;
		}
}
