package com.example.cricktingmaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cricktingmaterial.api.UsersAPI;
import com.example.cricktingmaterial.model.Users;
import com.example.cricktingmaterial.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyDetailActivity extends AppCompatActivity {

    Users users;
    private EditText etfname, etlname, etemail, etpassword, etaddress, etnumber;
    private TextView txtshop;
    TextView tvLoginNow1;
    public String userID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_detail);

        etemail = findViewById(R.id.etEmail1);
        etfname = findViewById(R.id.etFirstName1);
        etlname = findViewById(R.id.etLastName1);
        etpassword = findViewById(R.id.etPassword1);
        etaddress = findViewById(R.id.etAddress1);
        etnumber = findViewById(R.id.etPhone1);
        tvLoginNow1=findViewById(R.id.tvLoginNow1);


        tvLoginNow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyDetailActivity.this,Landind_Activity.class);
                startActivity(intent);
            }
        });

       // txtshop=findViewById(R.id.txt);
        loadCurrentUser();
    }

    private void loadCurrentUser() {
        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<Users> userCall = usersAPI.getUserDetails(Url.token);
        userCall.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MyDetailActivity.this, "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                users = response.body();
                userID=response.body().get_id();

//                 set id and pass value in ProductActivity
                ProductDetail_Activity.id = response.body().get_id();
                CartActivity.id=response.body().get_id();

//                Toast.makeText(MainActivity.this, "ID IS"+response.body().get_id(), Toast.LENGTH_SHORT).show();
                etfname.setText(response.body().getFname());
                etlname.setText(response.body().getLname());
                etemail.setText(response.body().getEmail());
                etpassword.setText(response.body().getPassword());
                etaddress.setText(response.body().getAddress());
                etnumber.setText(response.body().getNumber());
            }
            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.d("My message", "onFaliure" + t.getLocalizedMessage());
                Toast.makeText(MyDetailActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
