// Team name: Aurora 
package com.example.smartwm.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.smartwm.R;



public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean BackPressed = false;
    private CardView cvSensor;
    private CardView cvPaymentMethod;
    private CardView cvSchedulePickup;
    private CardView cvTrackPickup;
    private CardView cvCapture;
    private CardView cvOtherService;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Home");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cvSchedulePickup = findViewById(R.id.cvSchedulePickup);
        cvTrackPickup = findViewById(R.id.cvTrackPickup);
        cvCapture = findViewById(R.id.cvCapture);
        cvSensor = findViewById(R.id.cvSensor);
        cvPaymentMethod = findViewById(R.id.cvPaymentMethod);
        cvOtherService = findViewById(R.id.cvOtherService);

        cvSchedulePickup.setOnClickListener(this);
        cvTrackPickup.setOnClickListener(this);
        cvCapture.setOnClickListener(this);
        cvSensor.setOnClickListener(this);
        cvPaymentMethod.setOnClickListener(this);
        cvOtherService.setOnClickListener(this);


        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref sp =new SharedPref(HomeActivity.this);
                sp.logout();
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvSchedulePickup:
                Intent schedule = new Intent(HomeActivity.this, activity_schedule.class);
                startActivity(schedule);
                break;
            case R.id.cvTrackPickup:
                Intent track = new Intent(HomeActivity.this, TrackActivity.class);
                startActivity(track);
                break;
            case R.id.cvCapture:
                Intent capture = new Intent(HomeActivity.this, activity_camera.class);
                startActivity(capture);
                break;
            case R.id.cvSensor:
                Intent sensor = new Intent(HomeActivity.this, SensorActivity.class);
                startActivity(sensor);
                break;
            case R.id.cvPaymentMethod:
                Intent payment = new Intent(HomeActivity.this, PaymentActivity.class);
                startActivity(payment);
                break;
            case R.id.cvOtherService:
                Toast.makeText(HomeActivity.this, "Coming Soon...", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (BackPressed) {
            super.onBackPressed();
            return;
        }

        this.BackPressed = true;
        Toast.makeText(this, "Press Back Again To Exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                BackPressed=false;
            }
        }, 2000);
    }
}
