package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

//整个流程，
// 用户登陆，会被AuthenticationProcessingFilter拦截，调用AuthenticationManager的实现，
// 而且AuthenticationManager会调用ProviderManager来获取用户验证信息
// 不同的Provider调用的服务不同，因为这些信息可以是在数据库上，
// 可以是在LDAP服务器上，可以是xml配置文件上等），
// 如果验证通过后会将用户的权限信息封装一个User放到spring的全局缓存SecurityContextHolder中，
// 以备后面访问资源时使用。

// 访问资源（即授权管理），访问url时，会通过AbstractSecurityInterceptor拦截器拦截，
// 其中会调用FilterInvocationSecurityMetadataSource的方法来获取被拦截url所需的全部权限
// ，在调用授权管理器AccessDecisionManager，这个授权管理器会通过spring的全局缓存SecurityContextHolder
// 获取用户的权限信息，还会获取被拦截的url和被拦截url所需的全部权限，然后根据所配的策略
// （有：一票决定，一票否定，少数服从多数等），如果权限足够，则返回，权限不够则报错并调用权限不足页面。

@Service
public class UrlFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    public void setUrlAccessDecisionManager(UrlAccessDecisionManager urlAccessDecisionManager) {
        super.setAccessDecisionManager(urlAccessDecisionManager);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filterSecurityInterceptor1112");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("filterSecurityInterceptor2");
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }


    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        //fi里面有一个被拦截的url
        //里面调用UrlMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
        //再调用UrlAccessDecisionManager的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(fi);
        System.out.println("filterSecurityInterceptor3:" + fi.getRequest().getMethod() + ":" + fi.getRequestUrl());
        try {
            //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }


    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;

    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}
