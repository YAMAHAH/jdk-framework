package com.example.demo.output;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Map;

public class AjaxResponse implements Serializable {
    /**
     * 是否成功
     */
    private boolean flag;

    /**
     * 响应吗
     */
    private int code;

    /**
     * 业务状态码
     */
    private String stateCode;

    /**
     * 数据
     */
    private Object data;

    /**
     * 消息
     */
    private String msg;

    public AjaxResponse() {

    }

    public AjaxResponse(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public AjaxResponse(boolean flag, String msg, Object data) {
        this(flag, msg);
        this.data = data;
    }

    public AjaxResponse(boolean flag, String msg, String stateCode, Object data) {
        this(flag, msg, data);
        this.stateCode = stateCode;
    }

    public static AjaxResponse ok(Object data) {
        return ok("success", data);
    }

    public static AjaxResponse ok(String msg){
        return ok(msg,null);
    }

    public static AjaxResponse ok(String msg, Object data) {
        return new AjaxResponse(true, msg, data);
    }
    public static AjaxResponse ok(String stateCode,String msg, Object data) {
        return new AjaxResponse(true, msg,stateCode, data);
    }

    public static AjaxResponse error(String msg, String stateCode, Object data) {
        return new AjaxResponse(false, msg, stateCode, data);
    }

    public static AjaxResponse error(Map.Entry<String,String> code) {
        return new AjaxResponse(false,code.getValue(),code.getKey(),null);
    }

    public static AjaxResponse error(Map.Entry<String,String> code,Object data) {
        return new AjaxResponse(false,code.getValue(),code.getKey(),data);
    }

    public static AjaxResponse error(String msg, String stateCode) {
        return error(msg, stateCode, null);
    }

    public static AjaxResponse error(String msg, Object data) {
        return error(msg, "", data);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toJSON() {
        return JSON.toJSONString(this);
    }
}
