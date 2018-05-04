package com.example.demo.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtTokenUtil {
   public final static String header = "Authorization";

    final static Integer expiration = 7200;
    final static String issuer = "IATA";
    final static String authenticationPath = "/auth";
    final static String secret = "mySecret";

    public String generate(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret); //config.getJwt().getSecret()
            String token = JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis() + expiration * 1000))
                    .withSubject(username)
                    .sign(algorithm);
            return token;
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * @param token
     * @return username
     */
    public String verify(String token) {
        if (token == null) {
            return null;
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();
        } catch (Exception e) {
            throw new AccessDeniedException("JWT Authentication failure.");
        }
    }
}
