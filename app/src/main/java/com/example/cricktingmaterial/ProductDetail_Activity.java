package com.example.cricktingmaterial;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cricktingmaterial.url.Url;

import com.squareup.picasso.Picasso;

public class ProductDetail_Activity extends AppCompatActivity {
    ImageView dimgview;
    TextView tvdname, tvdprice, tvddesc, tvspecification,tvProximity;
    Button buy;
    Context mcontext;
    Button buttonCart;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);
        dimgview = findViewById(R.id.imgdis);
        tvddesc = findViewById(R.id.tvddesc);
        tvdprice = findViewById(R.id.tvdprice);
        tvdname = findViewById(R.id.tvdname);
        tvspecification = findViewById(R.id.tvspecification);
        buy = findViewById(R.id.buy);
        buttonCart = findViewById(R.id.cartnow);
        tvProximity =findViewById(R.id.tvProximity);
        proximity();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            String imgPath = Url.imagepath + (bundle.getString("image"));
            Picasso.get()
                    .load(imgPath)
                    .placeholder(R.drawable.logohomepng8)
                    .resize(220, 220)
                    .centerCrop()
                    .into(dimgview);

            tvdname.setText(bundle.getString("name"));
            tvdprice.setText("Rs: " + bundle.getString("price"));
            tvddesc.setText(bundle.getString("description"));
            tvspecification.setText(bundle.getString("specification"));
buy.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mcontext = getApplicationContext();
        Bundle bundle = getIntent().getExtras();

        Toast.makeText(ProductDetail_Activity.this, "" + bundle.getString("_id"), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ProductDetail_Activity.this, ConfirmOrderActivity.class);
        intent.putExtra("_id", bundle.getString("_id"));
        intent.putExtra("name", bundle.getString("name"));
        intent.putExtra("price", bundle.getString("price"));
        ProductDetail_Activity.this.startActivity(intent);
    }
});
        }


    }
    private void proximity() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener proxilistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] <= 4) {
                    tvProximity.setText("Object is near");
                } else {
                    tvProximity.setText("Object is far");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(proxilistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    }

