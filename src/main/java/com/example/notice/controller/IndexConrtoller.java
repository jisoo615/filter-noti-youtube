package com.example.notice.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.notice.model.User;
import com.example.notice.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Controller
public class IndexConrtoller {
	final private UserRepository userRepo;
	final private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@GetMapping({"/", ""})
	public String index() {
		//mustache 사용시 기본폴더=src/main/resources/
		//prefix=templates, sufix=.mustache
		//디펜던시 추가시 따로 설정 안해도됨
		//지금은 jsp그대로 사용할거임
		return "index";
	}
	
	@GetMapping("user")
	public @ResponseBody String user() {
		return "user";
	}
	@GetMapping("admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	@GetMapping("mannager")
	public @ResponseBody String mannager() {
		return "mannager";
	}
	
	//이 주소를 spring security에서 낚아챔-SecurityConfig 클래스 구현후 작동 안함
	@GetMapping("loginForm")
	public String login() {
		return "loginForm";
	}
	@GetMapping("joinForm")
	public String join() {
		return "joinForm";
	}
	@PostMapping("join")
	public String joinProc(User user) {
		System.out.println(user);
		user.setRole("ROLE_USER");
		String rawPwd = user.getPassword();
		String encPwd = bcryptPasswordEncoder.encode(rawPwd);
		user.setPassword(encPwd);
		userRepo.save(user);
		return "redirect:loginForm";
	}
	
	@Secured("ROLE_ADMIN")// SecurityConfig 에서 @EnableGlobalMethodSecurity enable해서 @Secured활성화됨
	@GetMapping("info")
	public @ResponseBody String info() {
		return "개인정보";
	}
	@PreAuthorize("hasRole('ROLE_MANNAGER') or hasRole('ROLE_ADMIN')") //얘도 위와 같음. 근데 기능은 메소드 실행 직전에 권한확인함
	@GetMapping("data")
	public @ResponseBody String data() {
		return "데이터 정보";
	}
}
