package com.example.notice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.notice.model.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer>{
	List<Follow> findByUsername(String username);
}
