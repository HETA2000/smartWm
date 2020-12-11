package com.example.smartwm.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartwm.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class activity_camera extends AppCompatActivity {

    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);


        Button btnLogout;
        CardView cvCamera;
         CardView cvUpload;
         CardView cvImages;
        final ImageView[] ivImage = new ImageView[1];
        final TextView[] tvSaveImg = new TextView[1];
        final OutputStream[] outputStream = new OutputStream[1];



            getSupportActionBar().setTitle("Capture Waste Image");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            btnLogout = findViewById(R.id.btnLogout);
            cvCamera = findViewById(R.id.cvCamera);
            cvUpload = findViewById(R.id.cvUpload);
            cvImages = findViewById(R.id.cvImage);
            ivImage[0] = findViewById(R.id.ivImage);
            tvSaveImg[0] = findViewById(R.id.tvSaveImg);

            cvCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    askCameraPermission();
                    cvImages.setVisibility(View.VISIBLE);
                }
            });

            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPref sp = new SharedPref(activity_camera.this);
                    sp.logout();
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                }
            });

            tvSaveImg[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BitmapDrawable drawable = (BitmapDrawable) ivImage[0].getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    File filepath = Environment.getExternalStorageDirectory();
                    File dir = new File(filepath.getAbsolutePath() + "/Smartwm/");
                    dir.mkdir();
                    File file = new File(dir, System.currentTimeMillis() + ".jpg");
                    try {
                        outputStream[0] = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream[0]);
                    Toast.makeText(activity_camera.this, "Saved", Toast.LENGTH_SHORT).show();
                    try {
                        outputStream[0].flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        outputStream[0].close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            cvUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 123);
                    cvImages.setVisibility(View.VISIBLE);
                }
            });
        }

        public void askCameraPermission() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE
                        , Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            } else {
                openCamera();
            }
        }

        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == 1) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    Toast.makeText(activity_camera.this, "Camera Permission Is Require", Toast.LENGTH_SHORT).show();
                }
            }
        }

        public void openCamera() {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, 1);
        }

        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == 1) {
                Bitmap img = (Bitmap) data.getExtras().get("data");
                ivImage.setImageBitmap(img);
            }

            if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
                Uri selectedImage = data.getData();
                ivImage.setImageURI(selectedImage);
            }
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            onBackPressed();
            return super.onOptionsItemSelected(item);
        }
    }