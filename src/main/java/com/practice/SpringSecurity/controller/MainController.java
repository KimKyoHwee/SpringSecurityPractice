package com.practice.SpringSecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(Model model){
        //서비스단에 구현 필수
        //아이디 가져오기
        String id=SecurityContextHolder.getContext().getAuthentication().getName();
        //사용자 권한(role) 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();
        //mustache에 아이디, 권한값 보내기
        model.addAttribute("id", id);
        model.addAttribute("role", role);
        return "main";
    }
}
