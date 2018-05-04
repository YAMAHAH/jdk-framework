package com.example.demo.core;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface CustomAnnotation {
    String[] value() default "";
    String remark() default "remark";
}
