package com.example.androidxfirebase;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidxfirebase.Data.adapter.AsistenciaAdapter;
import com.example.androidxfirebase.Data.adapter.EmpleadoAdapter;
import com.example.androidxfirebase.Data.dao.AsistenciaDao;
import com.example.androidxfirebase.Data.dao.EmpleadoDao;
import com.example.androidxfirebase.Data.model.Asistencia;
import com.example.androidxfirebase.Data.model.Empleado;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ListAsistenciaActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private AsistenciaDao asistenciaDao;
    private RecyclerView recyclerView;
    private AsistenciaAdapter asistenciaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_asistencia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseFirestore.getInstance();
        asistenciaDao = new AsistenciaDao(db);

        recyclerView = findViewById(R.id.rvUUser);

        asistenciaDao.getAll(new OnSuccessListener<List<Asistencia>>() {
            @Override
            public void onSuccess(List<Asistencia> empleados) {

                asistenciaAdapter = new AsistenciaAdapter(empleados, new OnSuccessListener<Asistencia>() {
                    @Override
                    public void onSuccess(Asistencia empleado) {
                        Toast.makeText(ListAsistenciaActivity.this, "Empleado: " + empleado.getNumero_documento_identidad(), Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setAdapter(asistenciaAdapter);
                for (Asistencia empleado : empleados) {
                    Log.d("ListAsistenciaActivity", "Empleado: " + empleado.getNumero_documento_identidad());
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