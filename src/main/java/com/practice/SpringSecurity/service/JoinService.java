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
        //db에 동일한 username이 존재하는지 검증
        boolean isUser=userRepository.existsByUsername(joinDto.getUsername());
        if(isUser) {//존재시 return(exception발생시켜야함)
            System.out.println("need to have exception");
            return;
        }
        //이후 회원저장로직
        UserEntity user=new UserEntity();
        user.setUsername(joinDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword())); //비밀번호 암호화 후 저장
        user.setRole("ROLE_USER"); //유저의 권한 지정
        userRepository.save(user);
    }
}
