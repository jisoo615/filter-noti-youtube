package com.example.notice.google.oauth;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleOAuthReq {
	private String client_id;
	private String redirect_uri;
	private String scope="https://www.googleapis.com/auth/youtube";
	private String response_type="code";
	private String access_type="offline";
	
	public MultiValueMap<String, String> toMultiValueMap(){
		var map = new LinkedMultiValueMap<String, String>();
		map.add("client_id", client_id);
		map.add("redirect_uri", redirect_uri);
		map.add("scope", scope);
		map.add("response_type", response_type);
		map.add("access_type", access_type);
		return map;
	}
}
