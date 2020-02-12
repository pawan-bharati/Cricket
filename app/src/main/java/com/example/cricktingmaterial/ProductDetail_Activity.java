package com.example.cricktingmaterial;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.cricktingmaterial.api.UsersAPI;
import com.example.cricktingmaterial.loginhandler.CartHandler;
import com.example.cricktingmaterial.model.Cart;
import com.example.cricktingmaterial.url.Url;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductDetail_Activity extends AppCompatActivity {
    ImageView dimgview;
    TextView tvdname, tvdprice, tvddesc, tvspecification,tvProximity;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    Button buy;
    Context mcontext;
    Button buttonCart;
    private SensorManager sensorManager;
    public static String id = null;
    String product_id = "";
    private FloatingActionButton fab;

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

            product_id = bundle.getString("_id");
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
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _userid = id;
                String _productid = product_id;
                CartHandler cartBll = new CartHandler();
                if (cartBll.checkcart(_userid, _productid)) {
                    Register();
                    Snackbar.make(view, "Added to Cart", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    scheduleNotification(getNotification("Buy now the product added in the cart box here ..."), 5000);

                } else {

                    Snackbar.make(view, "Already Added! Check cart", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    scheduleNotification(getNotification("You have already added  ..."), 5000);

                }
            }
        });


    }
    private void Register() {

        String _userid = id;
        String _productid = product_id;
        String name = tvdname.getText().toString();
        String price = tvdprice.getText().toString();
        String specification = tvspecification.getText().toString();
        String description = tvddesc.getText().toString();
        Cart cart = new Cart(_userid, _productid, name, price, description, specification);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UsersAPI employeeApi = retrofit.create(UsersAPI.class);
        Call<Void> voidCall = employeeApi.addcart(cart);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getApplicationContext(), "You have added to cart registered", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void proximity() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener proxilistener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[0] <= 4) {

                    Intent intent = new Intent(ProductDetail_Activity.this,MapsActivity.class);
                    startActivity(intent);
                } else {

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(proxilistener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void scheduleNotification(Notification notification, int delay) {
        Intent notificationIntent = new Intent(this, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }
    private Notification getNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, default_notification_channel_id);
        builder.setContentTitle("Check out Cart");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.cricketlogo);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }


    }

