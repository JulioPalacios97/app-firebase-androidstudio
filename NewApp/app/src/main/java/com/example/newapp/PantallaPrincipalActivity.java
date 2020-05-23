package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class PantallaPrincipalActivity extends AppCompatActivity {

    private Button mShowdataBtn, mLogoutBtn, mPacienteBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        mShowdataBtn = findViewById(R.id.showdatabtn);
        mLogoutBtn = findViewById(R.id.Logoutbtn);
        mPacienteBtn = findViewById(R.id.Pacientebtn);

        mAuth = FirebaseAuth.getInstance();


        //click btn register paciente
        mPacienteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PantallaPrincipalActivity.this, PacienteActivity.class));
                finish();
            }
        });

        //click btn to logout
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(PantallaPrincipalActivity.this, MainActivity.class));
                finish();
            }
        });

        //click btn to start showData
        mShowdataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PantallaPrincipalActivity.this, MostrarDatosActivity.class));
                finish();
            }
        });


    }
}
