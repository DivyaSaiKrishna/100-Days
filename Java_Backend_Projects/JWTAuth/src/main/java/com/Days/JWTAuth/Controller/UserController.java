package com.Days.JWTAuth.Controller;

import com.Days.JWTAuth.Bussiness.Model.User;
import com.Days.JWTAuth.Service.UserService;
import com.Days.JWTAuth.Utility.ModelUtility;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;



    @RequestMapping(value="/auth",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAuth(@RequestBody Map<String,Object> userMap){
        System.out.println(userMap);
        User user = null;
        try {
            user = ModelUtility.convertToModel(userMap, User.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        String authString =   userService.getAuth(user);
        return new ResponseEntity<String>(authString, HttpStatus.OK);
    }
    //eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJudWxsLW51bGwiLCJpYXQiOjE2ODc3NzA0MDksImlzcyI6IkpXVCBJU1NVRVIiLCJleHAiOjE2ODc3ODQ4MDl9.LOBV9c9dw1FycuONWVxJJtbDROcZZnDTOyS2G6JzM1mKZETOoFSxohXDMAQVknVzMzBdUNTQk8h2y42K--Sk4g
    @RequestMapping(value="/validate",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Jwt> getValidate(@RequestBody Map<String,Object> jwtToken){
        Jwt authString =  userService.getValidate((String) jwtToken.get("token"));
        System.out.println(jwtToken);
        System.out.println(authString);
        return new ResponseEntity<Jwt>(authString, HttpStatus.OK);
    }


}
