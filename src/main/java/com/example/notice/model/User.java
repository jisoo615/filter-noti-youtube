package com.example.notice.model;

import java.security.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;//ROLE_USER
	@DateTimeFormat
	private LocalDateTime createDate;
	
	@PrePersist
	public void preCreate() {
		this.createDate = LocalDateTime.now();
	}
	
}
