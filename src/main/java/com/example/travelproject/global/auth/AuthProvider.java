package com.example.travelproject.global.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AuthProvider implements AuthenticationProvider{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthUserService securityUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("[AuthProvider][authenticate] Start");

        String name= authentication.getName();
		String pwd= (String)authentication.getCredentials();
        log.info("name: "+name+" / pwd: "+pwd);

        // ID 검증 
        UserDetails userDetails = (AuthUserDto)securityUserService.loadUserByUsername(name);
        if(userDetails == null){
            throw new UsernameNotFoundException("There is no username >> "+name);
        }
        // PW 검증 
        else if (isNotMatches(pwd, userDetails.getPassword())) {
            throw new BadCredentialsException("Your password is incorrect.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // TODO Auto-generated method stub
        return false;
    }
    // 비밀번호 암호화에서 사용할 객체
    private boolean isNotMatches(String password, String encodePassword) {
        return !bCryptPasswordEncoder.matches(password, encodePassword);
    }
}
