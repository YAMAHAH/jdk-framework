package com.example.demo.output.person;

import org.springframework.beans.factory.annotation.Value;

public interface PersonSummary {
    @Value("#{target.name + ' ' + target.age}")
    String getFullName();
}
