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
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity
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
    private final SensorEventListener mSensorListener = new SensorEventListener() {

        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = (float) Math.sqrt((double) (x*x + y*y + z*z));
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta; // perform low-cut filter
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }
}
