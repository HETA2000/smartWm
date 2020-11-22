package com.example.smartwm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.smartwm.R;

public class SensorActivity extends AppCompatActivity {
    //Variable for logout button
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        getSupportActionBar().setTitle("Sensor");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            // Setting shared pref and calling intent
            @Override
            public void onClick(View v) {
                SharedPref sp =new SharedPref(SensorActivity.this);
                sp.logout();
                // Calling login activity on click
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
    // Code for displaying menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}