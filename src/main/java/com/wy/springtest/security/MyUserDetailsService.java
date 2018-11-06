package com.wy.springtest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserCheckService userCheckService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.wy.springtest.data.model.User sysUser = userCheckService.getUserByName(s);
        if (null == sysUser) {
            throw new UsernameNotFoundException(s);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String authority : sysUser.getAuthorities()) {
            authorities.add(new SimpleGrantedAuthority(authority));
        }
        return new User(sysUser.getName(), sysUser.getPass(), authorities);
    }
}
