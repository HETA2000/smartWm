package com.example.smartwm.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartwm.R;
import com.example.smartwm.database.DatabaseHelper;

public class SignupActivity extends AppCompatActivity {

    DatabaseHelper db;

    private TextView txtLogin;
    private EditText etName, etEmail, etContact, etPassword, etCPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this);

        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Sign Up");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etContact = findViewById(R.id.etContact);
        etPassword = findViewById(R.id.etPassword);
        etCPassword = findViewById(R.id.etCPassword);

        txtLogin = findViewById(R.id.txtLogin);


        btnRegister = findViewById(R.id.btnRegister);


        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String contact = etContact.getText().toString();
                String password = etPassword.getText().toString();
                String cPassword = etCPassword.getText().toString();

                if (name.equals("") || email.equals("") || contact.equals("") || password.equals("") || cPassword.equals("")) {
                    Toast.makeText(SignupActivity.this, "!Please Fill All Details.", Toast.LENGTH_LONG).show();
                } else {
                    if (password.equals(cPassword)) {
                        Boolean checkEmail = db.chkEmail(email);
                        if (checkEmail) {
                            Boolean insertData = db.insertData(name, email, contact, password);
                            if (insertData) {
                                Toast.makeText(getApplicationContext(), "Registered Successfully.", Toast.LENGTH_LONG).show();
                                etName.setText("");
                                etEmail.setText("");
                                etContact.setText("");
                                etPassword.setText("");
                                etCPassword.setText("");
                            }else {
                                Toast.makeText(getApplicationContext(), "Failed To Insert Data.", Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "!Email Already Exist.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "!Password Does Not Matched.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}