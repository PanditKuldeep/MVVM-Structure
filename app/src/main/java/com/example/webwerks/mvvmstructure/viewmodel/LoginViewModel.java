package com.example.webwerks.mvvmstructure.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import com.example.webwerks.mvvmstructure.model.request.LoginRequest;
import com.example.webwerks.mvvmstructure.model.response.LoginResponse;
import com.example.webwerks.mvvmstructure.service.RestServices;

public class LoginViewModel extends ViewModel {


    private MutableLiveData<LoginResponse> userMutableLiveData;


    public LiveData<LoginResponse> getUser(){

        if (userMutableLiveData == null){
            userMutableLiveData = new MutableLiveData<>();
        }

        return userMutableLiveData;
    }


    public void onButtonLogin(final LoginRequest user){

        final LoginResponse response = RestServices.getInstance().authenticUser(user);
        userMutableLiveData.setValue(response);

    }


    public void onButtonForget(final LoginRequest user){

        final LoginResponse response = RestServices.getInstance().forgetUser(user);
        userMutableLiveData.setValue(response);
    }

}
