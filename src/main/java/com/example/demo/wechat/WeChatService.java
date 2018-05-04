package com.example.demo.wechat;

import javax.servlet.http.HttpServletRequest;

public interface WeChatService {
    public String processRequest(HttpServletRequest request);
}
