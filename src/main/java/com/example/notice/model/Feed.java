package com.example.notice.model;

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
public class Feed {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String title;
	String username;//해당 계정 유저 (의 페이지)
	String writer;//글 작성자
	String content;// 글 내용
	
	@DateTimeFormat
	private LocalDateTime createDate;
	
	@PrePersist
	public void preCreate() {
		this.createDate = LocalDateTime.now();
	}

}
