package com.example.androidxfirebase.Data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.androidxfirebase.Data.model.Empleado;
import com.example.androidxfirebase.R;

import java.util.List;

public class EmpleadoAdapter extends RecyclerView.Adapter<EmpleadoAdapter.EmpleadoViewHolder> {

    private List<Empleado> empleadoList;
    private OnSuccessListener<Empleado> listener;

    public EmpleadoAdapter(List<Empleado> empleadoList, OnSuccessListener<Empleado> listener) {
        this.empleadoList = empleadoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmpleadoAdapter.EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emplado_list_layout, parent, false);
        return new EmpleadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadoAdapter.EmpleadoViewHolder holder, int position) {

        Empleado empleado = empleadoList.get(position);
        holder.userNameTextView.setText(empleado.getNombre());
        holder.userApellidoTextView.setText(empleado.getApellido());
        holder.userEdadTextView.setText(empleado.getEdad());
        holder.userEmailTextView.setText(empleado.getEmail());
        holder.userDireccionTextView.setText(empleado.getDireccion());
        holder.userTelefonoTextView.setText(empleado.getTelefono());
        holder.userCargoTextView.setText(empleado.getCargo());
        holder.userFnacimientoTextView.setText(empleado.getFnacimiento());
        holder.userFcontratacionTextView.setText(empleado.getFcontratacion());
        holder.userEstadoTextView.setText(empleado.getEstado());
        holder.userTipo_idTextView.setText(empleado.getTipo_id());
        holder.userNumero_idTextView.setText(empleado.getNumero_id());
        holder.userDepartamentoTextView.setText(empleado.getDepartamento());

        Glide.with(holder.itemView.getContext()).load(empleado.getImage()).into(holder.empleadoPhotoImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSuccess(empleado);
            }
        });
    }

    @Override
    public int getItemCount() {
        return empleadoList.size();
    }

    public class EmpleadoViewHolder extends RecyclerView.ViewHolder {
        private TextView userNameTextView;
        private TextView userApellidoTextView;
        private TextView userEdadTextView;
        private TextView userEmailTextView;
        private TextView userDireccionTextView;
        private TextView userTelefonoTextView;
        private TextView userCargoTextView;
        private TextView userFnacimientoTextView;
        private TextView userFcontratacionTextView;
        private TextView userTipo_idTextView;
        private TextView userEstadoTextView;
        private TextView userNumero_idTextView;
        private TextView userDepartamentoTextView;
        private ImageView empleadoPhotoImageView;

        public EmpleadoViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameTextView = itemView.findViewById(R.id.userNameTextView);
            userApellidoTextView = itemView.findViewById(R.id.userApellidoTextView);
            userEdadTextView = itemView.findViewById(R.id.userEdadTextView);
            userEmailTextView = itemView.findViewById(R.id.userEmailTextView);
            userDireccionTextView = itemView.findViewById(R.id.userDireccionTextView);
            userTelefonoTextView = itemView.findViewById(R.id.userTelefonoTextView);
            userCargoTextView = itemView.findViewById(R.id.userCargoTextView);
            userFnacimientoTextView = itemView.findViewById(R.id.userFnacimientoTextView);
            userFcontratacionTextView = itemView.findViewById(R.id.userFcontratacionTextView);
            userTipo_idTextView = itemView.findViewById(R.id.userTipo_idTextView);
            userEstadoTextView = itemView.findViewById(R.id.userEstadoTextView);
            userNumero_idTextView = itemView.findViewById(R.id.userNumero_idTextView);
            userDepartamentoTextView = itemView.findViewById(R.id.userDepartamentoTextView);
            empleadoPhotoImageView = itemView.findViewById(R.id.empleadoPhotoImageView);
        }
    }

}
