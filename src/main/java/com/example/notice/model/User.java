package com.example.notice.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;//ROLE_USER, ROLE_BUSINESS, ROLE_ADMIN, ROLE_MANNAGER
	@DateTimeFormat
	private LocalDateTime createDate;
	
	private String provider;
	private String providerId;
	
	@PrePersist
	public void preCreate() {
		this.createDate = LocalDateTime.now();
	}
	
	@Builder
	public User(String username, String password, String email, 
			String role, String provider, String providerId) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.provider = provider;
		this.providerId = providerId;
	}
		
}
