package com.example.newapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class HospitalActivity extends AppCompatActivity {
    //init EditText
    EditText mEditTextHospital, mEditTextEmail, mEditTextPassword;
    Button mButtonRegister;

    DatabaseReference databaseReference;


    //conecion con authentication
     FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        mEditTextHospital = (EditText) findViewById(R.id.hospitalEt);
        mEditTextEmail = (EditText) findViewById(R.id.emaildEt);
        mEditTextPassword = (EditText) findViewById(R.id.passwordEt);

        mButtonRegister = (Button) findViewById(R.id.registerbtn);

        databaseReference = FirebaseDatabase.getInstance().getReference("Hospital");
        firebaseAuth = FirebaseAuth.getInstance();

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEditTextEmail.getText().toString().trim();
                final String password = mEditTextPassword.getText().toString().trim();
                final String hospital = mEditTextHospital.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(HospitalActivity.this, "Te falto el campo de email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(HospitalActivity.this, "Te falto el campo de password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(hospital)){
                    Toast.makeText(HospitalActivity.this, "Te falto el campo de hospital", Toast.LENGTH_SHORT).show();
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(HospitalActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                   Registros information  = new Registros(
                                           email,
                                           password,
                                           hospital
                                   );

                                   FirebaseDatabase.getInstance().getReference("Hospital")
                                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                           .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           Toast.makeText(HospitalActivity.this, "Registro completo", Toast.LENGTH_SHORT).show();
                                           startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                       }

                                   });

                                } else {

                                }

                            }
                        });


            }
        });



    }


}
