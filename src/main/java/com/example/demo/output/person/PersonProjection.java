package com.example.demo.output.person;

public interface PersonProjection {
    String getName();
    Integer getAge();
    AddressSummary getAddress();

    interface AddressSummary {
        String getCity();
    }
}
