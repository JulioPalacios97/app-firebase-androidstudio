package com.example.newapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MostrarDatosActivity extends AppCompatActivity {

    RecyclerView recyclerViewArticulos;
    DatosPacientes mAdapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);

        //metodo back
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);


        recyclerViewArticulos = findViewById(R.id.recyclerArticulos);
        recyclerViewArticulos.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();

        //consulta para mostrar datos
        Query query = db.collection("Register");

        FirestoreRecyclerOptions<RegisterStore> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<RegisterStore>()
                .setQuery(query,RegisterStore.class).build();

        mAdapter = new DatosPacientes(firestoreRecyclerOptions, this);
        mAdapter.notifyDataSetChanged();
        recyclerViewArticulos.setAdapter(mAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}
