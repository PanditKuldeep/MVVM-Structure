package com.example.webwerks.mvvmstructure.model.request;

import android.util.Log;
import android.util.Patterns;

public class LoginRequest {

    String Email;
    String Password;


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
