package com.example.notice.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.notice.model.User;
import com.example.notice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/*시큐리티 설정(SecurityConfig)에서 loginProcessingUrl("/login") 해놔서
 * /login 요청오면 자동으로 UserDetailsService타입으로 IoC되어있는 
 * loadUserByUsername 메서드가 실행되도록 되어있다. 
 * */

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepo;
	
	@Override //아래 메서드 결과: SecuritySession(내부 Authentication(내부 UserDetails))
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepo.findByUsername(username);
		if(userEntity!=null) {
			return new PrincipalDetails(userEntity);
		}
		return null;
	}

}
