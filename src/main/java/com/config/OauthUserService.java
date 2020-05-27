/**   
* @Title: OauthUserService.java 
* @Package com.config 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2020-05-27 
*/
package com.config;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthorizationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/** 
* @ClassName: OauthUserService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author: zhuyj
* @date: 2020-05-27 
*/
public class OauthUserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private static final String INVALID_USER_INFO_RESPONSE_ERROR_CODE = "invalid_user_info_response";

	private static final ParameterizedTypeReference<Map<String, Object>> PARAMETERIZED_RESPONSE_TYPE =
			new ParameterizedTypeReference<Map<String, Object>>() {};

	private Converter<OAuth2UserRequest, RequestEntity<?>> requestEntityConverter = new OauthUserRequestEntityConverter();

	@SuppressWarnings("unchecked")
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// TODO Auto-generated method stub
		Assert.notNull(userRequest, "userRequest cannot be null");
		RequestEntity<?> request = this.requestEntityConverter.convert(userRequest);
		
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
				.getUserInfoEndpoint().getUserNameAttributeName();
		
		ResponseEntity<Map<String, Object>> response;
		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
			
			response = restTemplate.exchange(request, PARAMETERIZED_RESPONSE_TYPE);
		} catch (OAuth2AuthorizationException ex) {
			OAuth2Error oauth2Error = ex.getError();
			throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString(), ex);
		} catch (RestClientException ex) {
			OAuth2Error oauth2Error = new OAuth2Error(INVALID_USER_INFO_RESPONSE_ERROR_CODE,
					"An error occurred while attempting to retrieve the UserInfo Resource: " + ex.getMessage(), null);
			throw new OAuth2AuthenticationException(oauth2Error, oauth2Error.toString(), ex);
		}

		Map<String, Object> userAttributes = response.getBody();
		Map<String, Object> userInfo = (Map<String, Object>) userAttributes.get("info");
		Set<GrantedAuthority> authorities = new LinkedHashSet<>();
		authorities.add(new OAuth2UserAuthority("ROLE_ADMIN", userInfo));
		OAuth2AccessToken token = userRequest.getAccessToken();
		for (String authority : token.getScopes()) {
			authorities.add(new SimpleGrantedAuthority("SCOPE_" + authority));
		}

		return new DefaultOAuth2User(authorities, userInfo, userNameAttributeName);
	}

}
