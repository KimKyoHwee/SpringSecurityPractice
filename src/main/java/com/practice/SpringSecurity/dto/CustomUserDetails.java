package com.practice.SpringSecurity.dto;

import com.practice.SpringSecurity.entity.UserEntity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private UserEntity user;
    public CustomUserDetails(UserEntity user){
        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  //사용자의 권한 리턴(role값)
        Collection<GrantedAuthority> collection=new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() { //여기서 username은 PK로 지정하여야 한다.(중복이 아예 불가능한 필드인)
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { //사용자 계정이 만료되었는지 - true : 만료 안됨
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {  //사용자가 잠겨있는지 - true : 안잠김
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() { //자격(비밀번호) 증명 만료 여부 -true: 만료안됨
        return true;
    }
    @Override
    public boolean isEnabled() {  //사용자의 사용 가능 여부 : true : 사용가능
        return true;
    }
}
