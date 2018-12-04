package com.example.dao.entity;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

public class AuthUser implements Serializable {

    public AuthUser() {
    }
    private String userId;
    private UsernamePasswordToken token;
    private Long expirationTime;  //过期时间

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UsernamePasswordToken getToken() {
        return token;
    }

    public void setToken(UsernamePasswordToken token) {
        this.token = token;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }
}