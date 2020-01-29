package com.example.cricktingmaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Matrix;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashScreen_Activity extends AppCompatActivity {
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_);
imageView=findViewById(R.id.imgcricket);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();
                rotateImage(imageView);
            }
        },2000);

    }

    private  void  rotateImage (ImageView imageView){
        Matrix matrix= new Matrix();
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        matrix.postRotate((float)20,imageView.getDrawable().getBounds().width()/2,imageView.getDrawable().getBounds().height()/2);
        imageView.setImageMatrix(matrix);
    }
}
