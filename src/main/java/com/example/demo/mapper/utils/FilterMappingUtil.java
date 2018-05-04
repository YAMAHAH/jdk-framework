package com.example.demo.mapper.utils;


import com.example.demo.input.FilterInput;
import org.mapstruct.Context;
import org.mapstruct.MappingTarget;
import org.mapstruct.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

@Component
public class FilterMappingUtil {

    public FilterMappingUtil(){

    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Property {
    }

    @Property
    public String property(Map<String, Object> in) {
        return (String) in.get("sono");
    }

}
