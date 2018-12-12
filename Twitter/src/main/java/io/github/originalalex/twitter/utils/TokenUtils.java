package io.github.originalalex.twitter.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private  static final Algorithm ALGORITHM = Algorithm.HMAC256("dfpogtiuh34t9 dfg453452");
    private static final JWTVerifier VERIFIER = JWT.require(ALGORITHM)
            .withIssuer("auth0")
            .build();

    public static DecodedJWT decodeToken(String token) {
        try {
            DecodedJWT jwt = VERIFIER.verify(token);
            return jwt;
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public static String getToken(String user, String role) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("user", user);
        headers.put("role", role);
        try {
             return JWT.create()
                    .withIssuer("auth0")
                    .withIssuedAt(new Date())
                     .withHeader(headers)
                    .sign(ALGORITHM);
        } catch (JWTCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
