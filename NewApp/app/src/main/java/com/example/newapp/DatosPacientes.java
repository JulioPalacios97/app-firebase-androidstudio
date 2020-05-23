package com.example.newapp;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DatosPacientes extends FirestoreRecyclerAdapter<RegisterStore, DatosPacientes.ViewHolder> {

    Activity activity;
    FirebaseFirestore mFirestore;



    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DatosPacientes(@NonNull FirestoreRecyclerOptions<RegisterStore> options, Activity activity) {
        super(options);
        this.activity = activity;
        mFirestore = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull RegisterStore model) {
        DocumentSnapshot articuloDocument = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = articuloDocument.getId();

        holder.textViewPaciente.setText(model.getPaciente());
        holder.textViewEdad.setText(model.getEdad());
        holder.textViewProblema.setText(model.getProblema());
        holder.textViewCama.setText(model.getCama());

        holder.buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EditarActivity.class);
                intent.putExtra("articuloId", id);
                activity.startActivity(intent);
            }
        });

        holder.buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirestore.collection("Register").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(activity, "Los datos se eliminaron correctamente", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(activity, "Los datos no se pudieron eliminar", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_datos_pacientes,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //instanciar las vistas a usar
        TextView textViewPaciente;
        TextView textViewEdad;
        TextView textViewProblema;
        TextView textViewCama;

        Button buttonEditar, buttonEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            textViewPaciente = itemView.findViewById(R.id.pacienteText);
            textViewEdad = itemView.findViewById(R.id.edadText);
            textViewProblema = itemView.findViewById(R.id.TextViewPrblema);
            textViewCama = itemView.findViewById(R.id.TextViewCama);
            buttonEditar = itemView.findViewById(R.id.btnEditar);
            buttonEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
