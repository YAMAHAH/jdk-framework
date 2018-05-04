package com.example.demo.config;

public class EntryErrorInfo{
    private String errorMesage;
    private Object filed;
    private String code;
    private String objectName;

    public EntryErrorInfo(){

    }

    public String getErrorMesage() {
        return errorMesage;
    }

    public void setErrorMesage(String errorMesage) {
        this.errorMesage = errorMesage;
    }

    public Object getFiled() {
        return filed;
    }

    public void setFiled(Object filed) {
        this.filed = filed;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}
