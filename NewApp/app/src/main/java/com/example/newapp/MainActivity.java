package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //views
    Button  mLoginBtn, mHospitalBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init views
        mLoginBtn = findViewById(R.id.login_btn);
        mHospitalBtn = findViewById(R.id.hospital_btn);

        //handle register hospital button click
        mHospitalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start RegistryHospital HospitalActivity
                startActivity(new Intent(MainActivity.this, HospitalActivity.class));
            }
        });

        //handle register hospital button click
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });


    }
}
