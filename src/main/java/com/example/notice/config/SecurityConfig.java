package com.example.notice.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration //configuration 에서 AllArgsConstructor가 빠져서 bean등록 오류가 생성됨 -> @AllArgs..추가
@EnableWebSecurity//스프링 시큐리티 필터가 필터체인에 등록이 됨
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)// @Scured,@Pre/PostAuthorize 어노테이션 활성화-컨트롤러에서 부분 권한추가가능
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/user/**").authenticated() /*인증만되면 들어갈수 있는주소*/
			.antMatchers("/mannager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANNAGER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()
		.and()
		.formLogin()
			.loginPage("/loginForm")
			.usernameParameter("username") /*디폴트가 username임*/
			.loginProcessingUrl("/login") /*가입후자동로그인: /login주소가 호출되면 스프링시큐리티에서 낚아채(컨트롤러에 안만들어도됨) 대신 로그인진행함 */
			.defaultSuccessUrl("/");
	}
	
	@Bean//해당 메서드의 리턴되는 오브잭트를 IoC로 등록해준다. 
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
}
