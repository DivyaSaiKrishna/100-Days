package com.Days.JWTAuth.Utility;

import com.Days.JWTAuth.Bussiness.Model.User;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    //@Value("${app.jwt.secret}")
    private String secret_key = "krishnaabcdefghijklmnOPQRSTUVWXYZkrishnaabcdefghijklmnOPQRSTUVWXYZkrishnaabcdefghijklmnOPQRSTUVWXYZ";



    public String  generateJWTToken(User user){

        return Jwts.builder().
                setSubject(user.getUserName()+"-"+user.getEmailId()).
                setIssuedAt(new Date()).setIssuer(Constants.JWT_ISSUER).
                signWith(SignatureAlgorithm.HS512,secret_key).
                setExpiration(new Date(System.currentTimeMillis()+Constants.EXPIRATION_TIME)).
                compact();

    }

    public Jwt validateJWTToken(String jwtToken){
        try {
            return Jwts.parser().setSigningKey(secret_key).parse(jwtToken);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

}
