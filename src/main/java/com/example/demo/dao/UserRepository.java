package com.example.demo.dao;

import com.example.demo.models.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

//    List<User> getByMap(Map<String, Object> map);
//
//    List<User> getByRoleId(Map<String, Object> map);
//
//    User getById(Integer id);
//
//    Integer create(User user);
//
//    int update(User user);
}
