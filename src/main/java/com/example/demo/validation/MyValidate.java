package com.example.demo.validation;

import javax.validation.Constraint;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

//注解能放 在字段上
@Target(value={ElementType.FIELD,})
//注解 的保留策略
@Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
/* 验证注解通过 自定义验证类 实行验证 在这个类必须实现 ConstraintValidator<?,?>接口
 * 第一个泛型 是本注解的名称
 * 第二个泛型 是验证的对象类型
 *
 */
@Constraint(validatedBy={ValidateHandler.class})
public @interface MyValidate {
    //正则通配符
    public abstract java.lang.String regex();
    //验证信息
    public abstract java.lang.String message() default "{javax.validation.constraints.Pattern.message}";
    public abstract java.lang.Class[] groups() default {};
    public abstract java.lang.Class[] payload() default {};
}