package com.practice.SpringSecurity.service;

import com.practice.SpringSecurity.dto.CustomUserDetails;
import com.practice.SpringSecurity.entity.UserEntity;
import com.practice.SpringSecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override  //로그인시 security config가 검증을 위하여 username을 넣어서 보낸다.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user=userRepository.findByUsername(username);
        if(user!=null){
            return new CustomUserDetails(user);
        }
        return null;
    }
}
