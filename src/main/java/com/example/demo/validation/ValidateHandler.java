package com.example.demo.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*自定义验证实现 ConstraintValidator
        * 重写 initialize 和 isValid
        *
        * initialize 是初始化方法 可以做查询数据库等操作
        *
        * isValid 是验证处理方法，return true 表示验证通过
        *
        */
public class ValidateHandler implements ConstraintValidator<MyValidate,String> {
   // private Logger rootLogger = Logger.getLogger(ValidateHandler.class);
    private String regex;
    public void initialize(MyValidate info) {
        // TODO Auto-generated method stub
        //验证失败信息
       // rootLogger.debug(info.message());
        this.regex = info.regex();
    }
    public boolean isValid(String filedValue, ConstraintValidatorContext cvc) {
        //验证字段的值
       // rootLogger.debug(filedValue);
       // rootLogger.debug(cvc);
        //如果匹配上注解上的通配符 验证通过
        return Pattern.matches(regex, filedValue);
    }
}
