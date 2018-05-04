package com.example.demo.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

//@Component
public class SampleAuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        if (authentication.getName().equals(authentication.getCredentials())) {
//            return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), AUTHORITIES);
//        }
        System.out.println("AuthenticationManager Running.");
        return authentication;
       // throw new BadCredentialsException("Bad Credentials");
    }
//    private static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();
//
//    static {
//        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//
//    public Authentication authenticate(Authentication auth) throws AuthenticationException {
//        if (auth.getName().equals(auth.getCredentials())) {
//            return new UsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), AUTHORITIES);
//        }
//        throw new BadCredentialsException("Bad Credentials");
//    }
}
