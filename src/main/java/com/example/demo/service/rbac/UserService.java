package com.example.demo.service.rbac;

import com.example.demo.dao.UserRepository;
import com.example.demo.models.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required = true)
    private SessionRegistry sessionRegistry;

    @Autowired
    private UserRepository userRepository;

    public User getById(Integer id) {
        User user = userRepository.findById(id).get();
        return user;
    }
}