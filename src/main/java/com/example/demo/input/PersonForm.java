package com.example.demo.input;

import com.example.demo.validation.MyValidate;

import javax.validation.constraints.*;

public class PersonForm {
    @NotNull
    @Size(min=2, max=30)
    //@MyValidate(regex="^[a-zA-Z]+$.+",message="名字必须是字母开头")
    private String name;

    @NotNull
    @Min(18)
    private Integer age;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.age + ")";
    }
}
