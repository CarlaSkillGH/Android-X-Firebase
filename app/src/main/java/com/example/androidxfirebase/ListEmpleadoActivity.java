package com.example.androidxfirebase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidxfirebase.Data.adapter.EmpleadoAdapter;
import com.example.androidxfirebase.Data.dao.EmpleadoDao;
import com.example.androidxfirebase.Data.model.Empleado;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ListEmpleadoActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EmpleadoDao empleadoDao;
    private RecyclerView recyclerView;
    private EmpleadoAdapter empleadoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_empleado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseFirestore.getInstance();
        empleadoDao = new EmpleadoDao(db);

        recyclerView = findViewById(R.id.rvUser);

            empleadoDao.getAll(new OnSuccessListener<List<Empleado>>() {
                @Override
                public void onSuccess(List<Empleado> empleados) {

                    empleadoAdapter = new EmpleadoAdapter(empleados, new OnSuccessListener<Empleado>() {
                        @Override
                        public void onSuccess(Empleado empleado) {
                            Toast.makeText(ListEmpleadoActivity.this, "Empleado: " + empleado.getNombre(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    recyclerView.setAdapter(empleadoAdapter);
                    for (Empleado empleado : empleados) {
                        Log.d("ListEmpleadoActivity", "Empleado: " + empleado.getNombre());
                    }
                }
            });
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.terminate();
        db.clearPersistence();
    }
}