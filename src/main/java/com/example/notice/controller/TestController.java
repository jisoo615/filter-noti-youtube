package com.example.notice.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.notice.entity.User;
import com.example.notice.oauth.google.GoogleOAuth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("home")
@Controller
public class TestController {
	
	final private GoogleOAuth googleOAuth;
	
	@GetMapping("login")
	public String login(Model model) {
		String googleOAuthUri = googleOAuth.goGoogleOAuthUrl();
		log.info("인증1단계uri:{}", googleOAuthUri);
		model.addAttribute("googleOAuthUri", googleOAuthUri);
		return "home/login";
	}
	@GetMapping("mypage")
	public String login(@Param("code") String code, Model model) {
		log.info("code받고 엑세스토큰 받는 과정");
		User user = googleOAuth.getAccessToken(code);
		model.addAttribute("user", user);
		return "home/page";
	}

}
