package com.example.cricktingmaterial;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Landind_Activity extends AppCompatActivity {
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landind_);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
       NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        final Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorEventListener sel=new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] values=event.values;
                String xAxis="x:"+values[0];
                String yAxis="y:"+values[1];
                String zAxis="z:"+values[2];

                if(values[0]==-0.00&&values[1]==-9.81&&values[2]==0.00)
                {
                    Intent intent=new Intent(Landind_Activity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };
        if (sensor!=null)
        {
            sensorManager.registerListener(sel,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(Landind_Activity.this, "No sensor Found", Toast.LENGTH_SHORT).show();
        }
    }
    }


