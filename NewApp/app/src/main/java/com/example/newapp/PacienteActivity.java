package com.example.newapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PacienteActivity extends AppCompatActivity {

    //views
    EditText mPacienteEt, mEdadEt, mProblemEt, mCamaEt;
    Button mSaveBtn;


    //progress dialog
    ProgressDialog pd;

    //firestore instance
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        //metodo back
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        //action and its title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Agregar datos");



        //initialize views with its xml
        mPacienteEt = findViewById(R.id.pacienteEt);
        mEdadEt = findViewById(R.id.edadEt);
        mProblemEt = findViewById(R.id.problemEt);
        mCamaEt = findViewById(R.id.camaEt);


        mSaveBtn = findViewById(R.id.registerbtn);


        //progress dialog
        pd = new ProgressDialog(this);

        //firestore
        db = FirebaseFirestore.getInstance();

        //button to upload
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //input data
                String paciente = mPacienteEt.getText().toString().trim();
                String edad = mEdadEt.getText().toString().trim();
                String problema = mProblemEt.getText().toString().trim();
                String cama = mCamaEt.getText().toString().trim();


                //function call to upload data
                uploadData(paciente,edad,problema,cama);
            }
        });

    }

    private void uploadData(String paciente, String edad, String problema, String cama) {
        //set title of progress bar
        pd.setTitle("Agregando datos a FireStore");

        //show progress bar when user click save button
        pd.show();

        //random id for each data to be stored
        String id = UUID.randomUUID().toString();

        Map<String, Object>doc = new HashMap<>();
        doc.put("id", id); //id of data
        doc.put("paciente", paciente);
        doc.put("edad", edad);
        doc.put("problema", problema);
        doc.put("cama", cama);

        //add this data
        db.collection("Register").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //this will be called when data is added successfully
                        pd.dismiss();
                        Toast.makeText(PacienteActivity.this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //this will be called if there is any error while uploading
                        pd.dismiss();
                        //get and show  error message
                        Toast.makeText(PacienteActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
