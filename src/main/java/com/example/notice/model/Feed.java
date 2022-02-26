package com.example.notice.model;

import java.util.Date;

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
	String username;//글 작성자
	String writer;//없애기
	String content;// 글 내용
	
	@DateTimeFormat
	private Date createDate;
	
	@PrePersist
	public void preCreate() {
		this.createDate = new Date();
	}

}
