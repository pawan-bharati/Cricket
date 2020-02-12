package com.example.cricketwear;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginHandler {

    private String email;
    private String password;

    public LoginHandler(String email, String password) {
        this.email = email;
        this.password = password;
    }
}



