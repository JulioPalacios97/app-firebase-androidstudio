package com.example.newapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    
    EditText txtEmail, txtPassword;
    Button btn_Login;
    
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //metodo back
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        
        txtEmail = (EditText) findViewById(R.id.emaildEt);
        txtPassword=(EditText)findViewById(R.id.passwordEt);
        btn_Login = (Button) findViewById(R.id.registerbtn);
        
        firebaseAuth = FirebaseAuth.getInstance();
        
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();


                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "porfavor introduce correo", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "porfavor introduce contraseña", Toast.LENGTH_SHORT).show();
                }
                
                if (password.length()<6){
                    Toast.makeText(LoginActivity.this, "contraseña demasiada corta", Toast.LENGTH_SHORT).show();
                }


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),PantallaPrincipalActivity.class));
                                  
                                } else {
                                    Toast.makeText(LoginActivity.this, "Error de inicio de sesión para el usuario no disponible", Toast.LENGTH_SHORT).show();
                                }
                                
                            }
                        });
                
                
            }
        });
    }
}
