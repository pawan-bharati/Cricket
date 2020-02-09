package com.example.cricktingmaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cricktingmaterial.api.UsersAPI;
import com.example.cricktingmaterial.model.Order;
import com.example.cricktingmaterial.url.Url;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfirmOrderActivity extends AppCompatActivity {
    TextView tv1, tv2;
    EditText et1,et2;
    Button btnorder;
    String userid=null;
    int ordernumber=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        btnorder=findViewById(R.id.btnorder);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            userid=bundle.getString("_id");
            tv1.setText(bundle.getString("name"));
            tv2.setText(bundle.getString("price"));
        }

        btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(et1.getText())) {
                    et1.setError("Enter Address ");
                    return;
                }
                if (TextUtils.isEmpty(et2.getText())) {
                    et2.setError("Enter Number ");
                    return;
                }
                Order();
                showdialog();
            }
        });


    }

    private void Order() {
        Random random = new Random();
        int randomNumber = random.nextInt(80-65) + 65;
        ordernumber=(randomNumber);
        String name = tv1.getText().toString();
        String price = tv2.getText().toString();
        String address = et1.getText().toString();
        String number = et2.getText().toString();
        Order order = new Order(name,userid,price,address,number,ordernumber);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UsersAPI usersAPI = retrofit.create(UsersAPI.class);
        Call<Void> voidCall = usersAPI.orderthing(order);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(ConfirmOrderActivity.this, "You have successfully ordered an item", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ConfirmOrderActivity.this,Landind_Activity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ConfirmOrderActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void showdialog()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ConfirmOrderActivity.this);
        builder1.setMessage("Your Order id is "+ordernumber);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
