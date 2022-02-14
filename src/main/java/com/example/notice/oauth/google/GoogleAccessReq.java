package com.example.notice.oauth.google;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.Data;

@Data
public class GoogleAccessReq {
	
	private String code;
	private String client_id;
	private String client_secret;
	private String redirect_uri;
	private String grant_type="authorization_code";
	
	public MultiValueMap<String, String> toMultiValueMap() {
		var map = new LinkedMultiValueMap<String, String>();
		map.add("code", code);
		map.add("client_id", client_id);
		map.add("client_secret", client_secret);
		map.add("redirect_uri", redirect_uri);
		map.add("grant_type", grant_type);
		return map;
	}
}
