package com.example.cricketwear;

import android.content.Intent;

import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends WearableActivity {

    private TextView mTextView;
    Button btn2;
    private EditText etemail, etpwd;
    private  Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Enables Always-on
        etemail=findViewById(R.id.etuname);
        etpwd=findViewById(R.id.etpass);
        btnlogin=findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
        setAmbientEnabled();
    }

    private void userLogin() {
        String username = etemail.getText().toString();
        String password = etpwd.getText().toString();
        LoginHandler userLogin = new LoginHandler(username,password);
        StrictModeClass.StrictMode();
        UsersAPI userApi = com.example.cricketwear.Url.getInstance().create(UsersAPI.class);
        Call<SignUpResponse> userCall  = userApi.checkUser(username,password);
        userCall.enqueue(new Callback<SignUpResponse>() {


            @Override
            public void onResponse(Call<SignUpResponse> call, retrofit2.Response<SignUpResponse> response) {
                if(!response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, " User and Password is not correct", Toast.LENGTH_SHORT).show();
                    etemail.setText("");
                    etpwd.setText("");

                    return;
                }

                Toast.makeText(MainActivity.this, "Redirecting... ", Toast.LENGTH_SHORT).show();
                Url.token += response.body().getToken();
                Toast.makeText(getApplicationContext(),
                        "Login Successful", Toast.LENGTH_SHORT).show();
                Intent dashboard = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(dashboard);
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Error : " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}
