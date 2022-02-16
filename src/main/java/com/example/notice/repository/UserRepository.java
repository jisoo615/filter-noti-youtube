package com.example.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notice.model.User;

//@Repository 없어도 JpaRepository상속해서 괜찮음
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByUsername(String username);
}
