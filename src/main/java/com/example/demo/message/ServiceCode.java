package com.example.demo.message;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class ServiceCode {
    public static Map.Entry<String, String> FIELD_VALIDATE_ERROR = new SimpleEntry<String,String>("100000000", "参数错误");
    public static Map.Entry<String, String> GLOBAL_EXCEPTION_ERROR = new SimpleEntry<String,String>("100000001", "系统异常");
    public static Map.Entry<String, String> GLOBAL_UNKNOWN_ERROR = new SimpleEntry<String,String>("100000002", "未知错误");
}
