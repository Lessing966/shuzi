package com.example.demo.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {


    public static String createToken(String userId,String username) {
        return JWT.create().withAudience(userId)   //签发对象
                .withClaim("userid", userId)
                .withClaim("username", username)
                .sign(Algorithm.HMAC256(userId+"HelloLessing"));   //加密
    }

    /**
     * 通过载荷名字获取载荷的值
     */
    public static Claim getClaimByName(String token, String name){
        return JWT.decode(token).getClaim(name);
    }

}