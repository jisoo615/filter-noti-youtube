package com.example.notice.config.oauth;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.notice.config.auth.PrincipalDetails;
import com.example.notice.model.User;
import com.example.notice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService{
	final private UserRepository userRepo;
	
	//loadUser() = oauth2 로그인시,구글로부터 받은 UserRequest데이터에 대한 후처리 하는 메소드
	//메소드 종료시 @AuthienticationPrincipal 어노테이션이 만들어진다!!
	@Override 
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// username= goolge_3047387102293948, password=암호화(겟인데어), email=user@gmail.com, 
		//role="ROLE_USER", provider=google, providerId=3047387102293948
		// 구글로그인 완료-> code리턴(OAuth-client라이브러리가받아)-> accessToken요청해서받음
		// userRequest를 가지고 loadUser함수를 호출해->구글로부터 사용자 프로필을 받을 수 있다. 
		OAuth2User oauth2User = super.loadUser(userRequest);
		
		String provider = userRequest.getClientRegistration().getRegistrationId();//google
		String providerId = oauth2User.getAttribute("sub");
		String username = provider+"_"+providerId;//google_3938378292
		String email = oauth2User.getAttribute("email");
		String password = null;
		String role = "ROLE_USER";
		
		User userEntity = userRepo.findByUsername(username);
		if(userEntity==null) {
			userEntity = User.builder() /*@Builder*/
					.username(username).email(email).password(password)
					.role(role).provider(provider).providerId(providerId)
					.build();
			userRepo.save(userEntity);
		}
		return new PrincipalDetails(userEntity, oauth2User.getAttributes());//Authentication객체에 들어감
	}
	/*
super.loadUser(userRequest).ClientRegistration: ClientRegistration{ registrationId='google', clientId='...', clientSecret='...', 
		clientAuthenticationMethod=..., authorizationGrantType=org.springframework.security.oauth2.core.AuthorizationGrantType@5da5e9f3,
		redirectUri='{baseUrl}/{action}/oauth2/code/{registrationId}', scopes=[email, profile], 
		providerDetails=..., clientName='Google'
		}
AccessToken:엑세스토큰값
Attributes:{ sub=3047387102293948, name=..., given_name=.., family_name=.., 
		picture=https://lh3.googleuser..., email=..., email_verified=true, locale=ko
		}
	*/
}
