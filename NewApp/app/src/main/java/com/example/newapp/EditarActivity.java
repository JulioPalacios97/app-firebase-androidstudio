package com.example.newapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditarActivity extends AppCompatActivity {
    EditText  mProblemEt, mCamaEt;
    Button mUpdateBtn;

    private String articuloId;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        //metodo back
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);


        mProblemEt = findViewById(R.id.problemEt);
        mCamaEt = findViewById(R.id.camaEt);
        mUpdateBtn = findViewById(R.id.actualizarbtn);

        mFirestore = FirebaseFirestore.getInstance();
        articuloId = getIntent().getStringExtra("articuloId");
        obtenerDatos();

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarDatos();
            }
        });
    }

    private void obtenerDatos(){
        mFirestore.collection("Register").document(articuloId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String problema = documentSnapshot.getString("problema");
                    String cama = documentSnapshot.getString("cama");

                    mProblemEt.setText(problema);
                    mCamaEt.setText(cama);
                }
            }
        });
    }

    private void actualizarDatos(){
        String problema = mProblemEt.getText().toString();
        String cama = mCamaEt.getText().toString();

        Map<String,Object> map = new HashMap<>();
        //actulizar los campos del firestore
        map.put("problema", problema);
        map.put("cama", cama);
        //map.put("cama", "UNA NUEVA CAMA");

        mFirestore.collection("Register").document(articuloId).update(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(EditarActivity.this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditarActivity.this, "los datos no se pudieron actualizar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
