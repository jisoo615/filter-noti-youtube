package com.example.notice.oauth.google;

import lombok.Data;

@Data
public class GoogleAccessRes {
	private String access_token;
	private String token_type;
	private String expires_in;
	private String refresh_token;
}
