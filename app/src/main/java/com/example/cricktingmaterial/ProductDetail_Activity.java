package com.example.cricktingmaterial;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cricktingmaterial.url.Url;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class ProductDetail_Activity extends AppCompatActivity {
    ImageView dimgview;
    TextView tvdname, tvdprice, tvddesc, tvspecification;
    Button buyyy;
    Context mcontext;
    Button buttonCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);


        dimgview = findViewById(R.id.imgdis);
        tvddesc = findViewById(R.id.tvddesc);
        tvdprice = findViewById(R.id.tvdprice);
        tvdname = findViewById(R.id.tvdname);
        tvspecification = findViewById(R.id.tvspecification);
        buyyy = findViewById(R.id.buyyy);
        buttonCart = findViewById(R.id.cartnow);
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

        }
    }


    }

