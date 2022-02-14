package com.example.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.notice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
