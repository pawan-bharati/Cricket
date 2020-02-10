package com.example.cricktingmaterial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cricktingmaterial.adapter.CartsAdapter;
import com.example.cricktingmaterial.api.Products_Api;
import com.example.cricktingmaterial.model.Cart;

import com.example.cricktingmaterial.url.Url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerViewcart;
    public static String id=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerViewcart=findViewById(R.id.rvcart);
       getProduct();




    }

    public void getProduct() {
        String userid=id;
        Products_Api retrofitProductAPI = Url.getInstance().create(Products_Api.class);
        Call<List<Cart>> ProductsCall = retrofitProductAPI.getcart(userid);
        ProductsCall.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                // Toast.makeText(getActivity(), "RESPONSE" + response.body(), Toast.LENGTH_SHORT).show();
                CartsAdapter recyclerviewAdapter = new CartsAdapter(response.body(), getApplicationContext());
                RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getApplicationContext(), 1);
                recyclerViewcart.setLayoutManager(mlayoutManager);
                recyclerViewcart.setHasFixedSize(true);
                recyclerViewcart.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {

            }
        });


    }
}
