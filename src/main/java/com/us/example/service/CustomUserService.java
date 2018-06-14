package com.us.example.service;

import com.us.example.domain.UserInfo;
import com.us.example.repository.UserRepository;
import com.us.example.util.MD5Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CustomUserService implements UserDetailsService {

    Logger logger= Logger.getLogger(CustomUserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        com.us.example.domain.User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("User Name doesn't exist");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        logger.info("user "+user.getUsername()+" has roles"+user.getRole());
        UserInfo userInfo=new UserInfo(user.getUsername(),user.getPassword(), authorities);
        userInfo.setFirstName(user.getFirstname());
        userInfo.setLastName(user.getLastname());
        userInfo.setAddress(user.getHomeaddress());
        userInfo.setEmail(user.getEmail());
        userInfo.setTelephone(user.getTelephone());
        userInfo.setNearestembassy(user.getNearestembassy());
        userInfo.setUserid(user.getId());
        userInfo.setRole(user.getRole());
        return  userInfo;

    }

    @Transactional
    public com.us.example.domain.User save(com.us.example.domain.User user) {
        PasswordEncoder passwordEncoder =new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String)rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String)rawPassword));
            }
        };
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public void signin(com.us.example.domain.User user) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(user));
    }

    private Authentication authenticate(com.us.example.domain.User user) {
        return new UsernamePasswordAuthenticationToken(createUser(user), null, Collections.singleton(createAuthority(user)));
    }

    private User createUser(com.us.example.domain.User user) {
        return new User(user.getEmail(), user.getPassword(), Collections.singleton(createAuthority(user)));
    }

    private GrantedAuthority createAuthority(com.us.example.domain.User user) {

        return new SimpleGrantedAuthority(user.getRole());
    }
}
