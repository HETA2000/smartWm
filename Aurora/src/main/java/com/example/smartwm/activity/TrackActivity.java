// Team name: Aurora 
package com.example.smartwm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smartwm.R;

public class TrackActivity extends AppCompatActivity {
    // Declaring variables for getting buttons from the layout
    private Button btnLogout, btnCall, btnMsg,btnImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        getSupportActionBar().setTitle("Track Pickup");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Variables for layout and buttons
        btnLogout = findViewById(R.id.btnLogout);
        btnCall = findViewById(R.id.btnCall);
        btnMsg = findViewById(R.id.btnMsg);
        btnImg=  findViewById(R.id.btnBut);

        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:43.6532,79.3832"));
                Intent chooser=Intent.createChooser(intent,"Launch Map");
                startActivity(chooser);

            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref sp =new SharedPref(TrackActivity.this);
                sp.logout();
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:0123456789"));
                startActivity(intent);
            }
        });

        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Coming Soon...",Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
