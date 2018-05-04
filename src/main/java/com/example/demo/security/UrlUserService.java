package com.example.demo.security;

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.rbac.PermissionRepository;
import com.example.demo.models.auth.User;
import com.example.demo.models.rbac.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UrlUserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) {
        //重写loadUserByUsername 方法获得 userdetails 类型用户
        System.out.println("UserDetailsService1");
        User user = userRepository.findByUsername(userName);
        if (user != null) {
            List<Permission> permissions = permissionRepository.findAllById(Arrays.asList(user.getId()));
            List<UrlGrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {
                    UrlGrantedAuthority grantedAuthority = new UrlGrantedAuthority(permission.getPermissionUrl(),permission.getMethod());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            user.setGrantedAuthorities(grantedAuthorities);
            return user;
        } else {
            throw new UsernameNotFoundException("admin: " + userName + " do not exist!");
        }
    }
}