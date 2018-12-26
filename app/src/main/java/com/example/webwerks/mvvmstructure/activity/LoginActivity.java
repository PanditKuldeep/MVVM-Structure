package com.example.webwerks.mvvmstructure.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webwerks.mvvmstructure.R;
import com.example.webwerks.mvvmstructure.model.response.LoginResponse;
import com.example.webwerks.mvvmstructure.viewmodel.LoginViewModel;
import com.example.webwerks.mvvmstructure.model.request.LoginRequest;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword,etEmailForget;
    Button btnLogin,btnForget;
    LinearLayout llLogin,llForget;
    TextView txtForget;
    LoginViewModel model;
    LoginRequest user = new LoginRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

    }

    private void initView() {

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etEmailForget = findViewById(R.id.etEmailForget);

        llLogin = findViewById(R.id.llLogin);
        llForget = findViewById(R.id.llForget);

        txtForget = findViewById(R.id.txtForget);
        txtForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llLogin.setVisibility(View.GONE);
                llForget.setVisibility(View.VISIBLE);
            }
        });

        loginView();

        forgetView();


        model.getUser().observe(this, new Observer<LoginResponse>(){
            @Override
            public void onChanged(@Nullable LoginResponse response) {

                if (response.getStatus().equals(-1)) {

                    Toast.makeText(LoginActivity.this, "Email or password is wrong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, response.user_msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void loginView(){
        model = ViewModelProviders.of(this).get(LoginViewModel.class);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                user.setEmail(etEmail.getText().toString());
                user.setPassword(etPassword.getText().toString());

                model.onButtonLogin(user);
                etEmail.setText("");
                etPassword.setText("");
                user.setEmail("");
                user.setPassword("");
            }
        });

    }

    private void forgetView() {


        btnForget = findViewById(R.id.btnForget);
        btnForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llForget.setVisibility(View.GONE);
                llLogin.setVisibility(View.VISIBLE);
                user.setEmail(etEmailForget.getText().toString());
                model.onButtonForget(user);
                etEmailForget.setText("");
                user.setEmail("");
            }
        });
    }

}
