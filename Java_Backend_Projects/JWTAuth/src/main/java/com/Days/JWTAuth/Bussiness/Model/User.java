package com.Days.JWTAuth.Bussiness.Model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String id;
    private String userName;
    private String emailId;
    private String password;
    private Boolean isActive;
    private Date createdDate;
}
