package com.example.demo.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class CustomBeanConfig {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public SessionFactory sessionFactory(){
        if(entityManagerFactory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory.");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }
}
