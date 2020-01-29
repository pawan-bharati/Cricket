package com.example.cricktingmaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    EditText etLoginEmail,etLoginPassword;
    Button btnRegister;
    TextView tvSignUpNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLoginEmail=findViewById(R.id.etLoginEmail);
        etLoginPassword=findViewById(R.id.etLoginPassword);
        btnRegister=findViewById(R.id.btnRegister);
        tvSignUpNow=findViewById(R.id.tvSignupNow);

        tvSignUpNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
