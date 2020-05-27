package ru.javalab.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class JwtToken {

    public static String createToken(String id){
        Algorithm algorithm = Algorithm.HMAC256("javalab");
        String token = JWT.create()
                .withIssuer(id)
                .sign(algorithm);
        return token;
    }

    public static Long getIdFromJwt(String token){
        Algorithm algorithm = Algorithm.HMAC256("javalab");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return Long.valueOf(jwt.getIssuer());
    }
}
