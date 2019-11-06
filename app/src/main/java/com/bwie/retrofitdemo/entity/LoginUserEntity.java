package com.bwie.retrofitdemo.entity;

public class LoginUserEntity {
    public String message;
    public String status;

    public User result;

    public static class User{
        public String phone ;
        public String sessionId;
        public String userId;
    }
}
