package com.example.cricktingmaterial;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cricktingmaterial.loginhandler.LoginHandler;
import com.example.cricktingmaterial.strictmode.StrictModeClass;

public class MainActivity extends AppCompatActivity {
EditText etusername,etloginpassword;
Button btnlogin;
TextView tvRegisterLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etusername=findViewById(R.id.etusername);
        etloginpassword=findViewById(R.id.etloginpassword);
        btnlogin=findViewById(R.id.btnlogin);
        tvRegisterLink=findViewById(R.id.tvRegisterLink);

        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
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
        String password=etloginpassword.getText().toString();

        LoginHandler loginHandler=new LoginHandler();

        StrictModeClass.StrictMode();

        if (loginHandler.checkUser(email,password)){
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();

            Intent intent =new Intent(MainActivity.this,MyDetailActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Either email or password is incorrect", Toast.LENGTH_SHORT).show();
            etusername.requestFocus();
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                v.vibrate(500);
            }
        }
    }
}






