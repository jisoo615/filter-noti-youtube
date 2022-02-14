package com.example.notice.google.oauth;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

import com.example.notice.entity.User;
import com.example.notice.google.dto.Channel;
import com.example.notice.repository.UserRepository;
import com.example.restaurant.naver.dto.SearchLocalRes;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty.Type;

import io.swagger.annotations.Info;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.spring.web.plugins.DefaultResponseTypeReader;

@Slf4j
@RequiredArgsConstructor
@Component
public class GoogleOAuth {
	
	final private UserRepository userRepo;
	
	@Value("${google.oauth.url}")
	private String goolgeOAuthUrl;
	@Value("${google.oauth.client.id}")
	private String clientId;
	@Value("${google.oauth.client.secret}")
	private String clientSecret;
	@Value("${google.oauth.redirect}")
	private String redirectUri;
		
	public String goGoogleOAuthUrl() {
		GoogleOAuthReq req = new GoogleOAuthReq();
		req.setClient_id(clientId);
		req.setRedirect_uri(redirectUri);
		String uri = UriComponentsBuilder.fromUriString(goolgeOAuthUrl)
				.queryParams(req.toMultiValueMap())
				.build()
				.encode()
				.toUriString();
		return uri;
	}
	
	public User getAccessToken(String code) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		GoogleAccessReq req = new GoogleAccessReq();
		req.setCode(code);
		req.setClient_id(clientId);
		req.setClient_secret(clientSecret);
		req.setRedirect_uri(redirectUri);
		MultiValueMap<String, String> body = req.toMultiValueMap();
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);
		
		var responseType = new ParameterizedTypeReference<GoogleAccessRes>() {};
		
		var responseEntity = restTemplate.exchange("https://accounts.google.com/o/oauth2/token", 
						HttpMethod.POST ,entity, responseType);
		
		GoogleAccessRes res = responseEntity.getBody();
		log.info("accessToken:{}", res.getAccess_token());
		User user = new User();
		user.setRefreshToken(res.getRefresh_token());
		user.setAccessToken(res.getAccess_token());
		user.setExpiresIn(Integer.parseInt(res.getExpires_in()));
		
		userRepo.save(user);
		System.out.println(responseEntity.getStatusCode().toString());
		
		return user;
	}
	
	public void getMyChannel(User user) {
		String uri = UriComponentsBuilder.fromUriString("https://www.googleapis.com/youtube/v3/channels")
				.queryParam("part", "snippet")
				.queryParam("mine", "true")
				.build()
				.encode()
				.toUriString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+user.getAccessToken());
		var httpEntity = new HttpEntity<>(headers);
		var responseType = new ParameterizedTypeReference<Channel>() {};
		var responseEntity = new RestTemplate().exchange(uri, HttpMethod.POST, httpEntity, responseType);
	}
}
