package com.example.notice.google.oauth;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.example.notice.entity.User;
import com.example.notice.google.dto.Channel;
import com.example.notice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

		System.out.println(responseEntity.getStatusCode().toString());
		User newUser = getMyChannel(user);
		userRepo.save(newUser);
		return newUser;
	}
	
	public User getMyChannel(User user) {
		String uri = UriComponentsBuilder.fromUriString("https://www.googleapis.com/youtube/v3/channels")
				.queryParam("part", "snippet")
				.queryParam("mine", "true")
				.build()
				.encode()
				.toUriString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+user.getAccessToken());
		var httpEntity = new HttpEntity<>(headers);
		var responseEntity = new RestTemplate().exchange(
				uri, HttpMethod.GET, httpEntity, String.class);
		// items.id, items.snippet.title 가져와야함
		try {
			JSONObject jsonObject = new JSONObject(responseEntity.getBody());
			JSONArray items = jsonObject.getJSONArray("items");
			log.info("myChannel: {}", items.get(0));
			JSONObject item = items.getJSONObject(0);
			user.setChannelId(item.getString("id"));
			log.info("id:{}", user.getChannelId());
			user.setTitle(item.getJSONObject("snippet").getString("title"));
			log.info("title:{}", user.getTitle());
		} catch (JSONException e) {
			log.info("my channel jsonObject parsing error");
		}
		return user;
		
	}
	
	public User findById(Integer id) {
		if(userRepo.findById(id).isPresent()) {
			return userRepo.findById(id).get();
		}
		else {
			var user = new User();
			user.setTitle("알수없는 이용자");
			return user;
		}
	}
}
