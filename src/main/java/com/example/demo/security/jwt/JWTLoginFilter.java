package com.example.demo.security.jwt;

import com.example.demo.models.auth.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();
        res.addHeader("Authorization", "Bearer " + token);
        //直接以流的方式写入body
        OutputStream out = res.getOutputStream();
        out.write(token.getBytes("UTF-8"));
        out.flush();
//        res.setContentType("application/json");
//        res.setCharacterEncoding("UTF-8");
//        req.setCharacterEncoding("UTF-8");
//
//        /**
//         * 接收json
//         */
//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                (ServletInputStream) req.getInputStream(), "utf-8"));
//        StringBuffer sb = new StringBuffer("");
//        String temp;
//        while ((temp = br.readLine()) != null) {
//            sb.append(temp);
//        }
//        br.close();
//        System.out.println(sb.toString());
//
//
//        /**
//         * 返回json
//         */
//        // 转成数据流
//        InputStream is = new ByteArrayInputStream(
//                "{\"name\":\"账单\"}".getBytes());
//        // 输出到画面
//        ServletOutputStream op = res.getOutputStream();
//        int len;
//        byte[] buff = new byte[4096];
//        while ((len = is.read(buff)) != -1) {
//            op.write(buff, 0, len);
//        }
//        op.flush();

    }
}
