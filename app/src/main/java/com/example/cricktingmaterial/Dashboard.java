package com.example.cricktingmaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toast.makeText(Dashboard.this, "Hello", Toast.LENGTH_SHORT).show();
        lightInstance();
    }
    private void lightInstance(){
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        SensorEventListener lightlistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if( event.sensor.getType() == Sensor.TYPE_LIGHT){
                    Toast.makeText(Dashboard.this, "onSensor Change :"+ event.values[0], Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(lightlistener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
