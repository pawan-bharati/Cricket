package com.example.cricktingmaterial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cricktingmaterial.api.UsersAPI;
import com.example.cricktingmaterial.model.UsersCUD;
import com.example.cricktingmaterial.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    EditText etFirstName,etLastName,etEmail,etAddress,etPhone,etPassword;
    Button btnRegister;
    TextView tvLoginNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName=findViewById(R.id.etFirstName);
        etLastName=findViewById(R.id.etLastName);
        etEmail=findViewById(R.id.etEmail);
        etAddress=findViewById(R.id.etAddress);
        etPhone=findViewById(R.id.etPhone);
        etPassword=findViewById(R.id.etPassword);
        btnRegister=findViewById(R.id.btnRegister);
        tvLoginNow=findViewById(R.id.tvLoginNow);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        tvLoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private void register() {
        String fname = etFirstName.getText().toString();
        String lname = etLastName.getText().toString();
        String address = etAddress.getText().toString();
        String number = etPhone.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        //  String security=etSecurityQuestion.getText().toString();

        UsersCUD employee = new UsersCUD(fname, lname, address, number, email, password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsersAPI employeeApi = retrofit.create(UsersAPI.class);

        Call<Void> voidCall = employeeApi.registerEmployee(employee);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RegisterActivity.this, "You have been registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}