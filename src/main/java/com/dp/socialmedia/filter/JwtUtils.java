package com.dp.socialmedia.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

    public String getUsername(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        var decodedJWT = verifier.verify(token);

        return decodedJWT.getSubject();
    }

    public String createToken(UserDetails user, Long duration) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .sign(algorithm);
    }
}
