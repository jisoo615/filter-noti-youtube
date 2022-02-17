package com.example.notice.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.example.notice.model.User;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/* 스프링시큐리티가 /login 주소요청오면 낚아채 로그인진행시고
 * 로그인 진행이 완료되면 security session(세션내에 시큐리티세션이 있음.Security ContextHolder키값을 가짐)
 * 에 넣어줌
 * security session에 들어갈 객체는(오브젝트타입) Authentication타입의 객체여야 하고,
 * Authentication객체 안에는 유저정보(ex.User)가 있어야 하는데 이는 UserDetails타입객체여야 한다. 
 * -- security session => Authentication => UserDetails
 * */
@Data
public class PrincipalDetails implements UserDetails, OAuth2User{
	
	private User user; //콤포지션: 상속의 문제피하기 위해 private 필드로 기존 클래스의 인스턴스를 참조하게함
	private Map<String, Object> attributes;
	
	public PrincipalDetails(User user){
		this.user = user;
	}
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	@Override  //해당 유저의 권한을 리턴함
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {//oauth2User
		return attributes;
	}

	@Override
	public String getName() {//oauth2User
		return null;
	}
	
	
}
