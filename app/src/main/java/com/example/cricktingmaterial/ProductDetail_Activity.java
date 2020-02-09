package com.example.cricktingmaterial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cricktingmaterial.url.Url;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class ProductDetail_Activity extends AppCompatActivity {
    ImageView dimgview;
    TextView tvdname, tvdprice, tvddesc, tvspecification;
    Button buy;
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
        buy = findViewById(R.id.buy);
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


    }

