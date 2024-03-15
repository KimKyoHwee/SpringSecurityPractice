package com.practice.SpringSecurity.repository;

import com.practice.SpringSecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username); //username가진사람 있는지 체크 true:존재
    UserEntity findByUsername(String username);
}
