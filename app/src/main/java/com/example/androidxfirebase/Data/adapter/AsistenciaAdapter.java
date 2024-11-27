package com.example.androidxfirebase.Data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidxfirebase.Data.model.Asistencia;
import com.example.androidxfirebase.Data.model.Empleado;
import com.example.androidxfirebase.R;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class AsistenciaAdapter extends RecyclerView.Adapter<AsistenciaAdapter.AsistenciaViewHolder>  {

    private List<Asistencia> asistenciaList;
    private OnSuccessListener<Asistencia> listener;

    public AsistenciaAdapter(List<Asistencia> asistenciaList, OnSuccessListener<Asistencia> listener) {
        this.asistenciaList = asistenciaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AsistenciaAdapter.AsistenciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_asistencia_adapter, parent, false);
        return new AsistenciaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AsistenciaAdapter.AsistenciaViewHolder holder, int position) {

        Asistencia empleado = asistenciaList.get(position);
        holder.userIdentidadTextView.setText(empleado.getNumero_documento_identidad());
        holder.userEntradaTextView.setText(empleado.getFecha_entrada());
        holder.userSalidaTextView.setText(empleado.getFecha_salida());
        holder.userNovedadesTextView.setText(empleado.getNovedades_asistencia());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSuccess(empleado);
            }
        });
    }

    @Override
    public int getItemCount() {
        return asistenciaList.size();
    }

    public class AsistenciaViewHolder extends RecyclerView.ViewHolder {
        private TextView userIdentidadTextView;
        private TextView userEntradaTextView;
        private TextView userSalidaTextView;
        private TextView userNovedadesTextView;


        public AsistenciaViewHolder(@NonNull View itemView) {
            super(itemView);
            userIdentidadTextView = itemView.findViewById(R.id.userIdentidadTextView);
            userEntradaTextView = itemView.findViewById(R.id.userEntradaTextView);
            userSalidaTextView = itemView.findViewById(R.id.userSalidaTextView);
            userNovedadesTextView = itemView.findViewById(R.id.userNovedadesTextView);

        }
    }

}
