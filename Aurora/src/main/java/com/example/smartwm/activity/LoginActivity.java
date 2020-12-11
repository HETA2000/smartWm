package com.example.smartwm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartwm.R;
import com.example.smartwm.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    private TextView txtSignup, tvForgot;
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        db = new DatabaseHelper(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        txtSignup = findViewById(R.id.txtSignup);
        btnLogin = findViewById(R.id.btnLogin);

        tvForgot = findViewById(R.id.tvForgot);

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                Boolean checkLogin = db.Login(email, password);

                if (email.equals("") || password.equals("")) {
                    Toast.makeText(LoginActivity.this, "!Please Fill All Details.", Toast.LENGTH_LONG).show();
                } else {
                    if (checkLogin) {
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, "!Email or Password Does Not Match.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}
