package com.Days.JWTAuth.Service;

import com.Days.JWTAuth.Bussiness.Model.User;
import com.Days.JWTAuth.Utility.JWTUtil;
import io.jsonwebtoken.Jwt;
import org.springframework.stereotype.Service;


import java.util.Map;

@Service
public class UserService {

    public String getAuth(User user){
        return new JWTUtil().generateJWTToken(user);
    }

    public Jwt getValidate(String jwtToken){
        return new JWTUtil().validateJWTToken(jwtToken);
    }


}
