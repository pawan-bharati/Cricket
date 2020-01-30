package com.example.cricktingmaterial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cricktingmaterial.loginhandler.LoginHandler;
import com.example.cricktingmaterial.strictmode.StrictModeClass;

public class MainActivity extends AppCompatActivity {
EditText etusername,etpassword;
Button btnlogin;
TextView tvLoginLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusername=findViewById(R.id.etusername);
        etpassword=findViewById(R.id.etpassword);
        btnlogin=findViewById(R.id.btnlogin);
        tvLoginLink=findViewById(R.id.tvLoginLink);

        tvLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               login();
            }
        });


    }
    private void login() {
        String email=etusername.getText().toString();
        String password=etpassword.getText().toString();

        LoginHandler loginHandler=new LoginHandler();

        StrictModeClass.StrictMode();

        if (loginHandler.checkUser(email,password)){
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Either email or password is incorrect", Toast.LENGTH_SHORT).show();
            etusername.requestFocus();
        }
    }
}






