package com.example.travelproject.global.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.travelproject.domain.user.model.UserEntity;
import com.example.travelproject.domain.user.model.UserRepository;

@Service
public class AuthUserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        UserEntity userEntity = userRepository.getUserDtoByName(username);
        
        if(userEntity != null){
            return new AuthUserDto(userEntity);
        }

    return null;
    }
}
