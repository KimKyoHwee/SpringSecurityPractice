package com.practice.SpringSecurity.service;

import com.practice.SpringSecurity.dto.JoinDto;
import com.practice.SpringSecurity.entity.UserEntity;
import com.practice.SpringSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public void joinProcess(JoinDto joinDto){
        UserEntity user=new UserEntity();
        user.setUsername(joinDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword())); //비밀번호 암호화 후 저장
        user.setRole("ROLE_USER"); //유저의 권한 지정
        userRepository.save(user);
    }
}
