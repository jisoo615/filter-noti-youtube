package com.example.notice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notice.model.Feed;

public interface FeedRepository extends JpaRepository<Feed, Integer>{
	List<Feed> findByUsernameOrderByCreateDate(String username);
}
