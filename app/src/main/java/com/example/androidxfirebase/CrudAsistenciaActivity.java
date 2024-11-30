package com.example.androidxfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CrudAsistenciaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crud_asistencia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAsistencias = findViewById(R.id.btnAsistenciaa);
        Button btnCrearAsistencias = findViewById(R.id.btnCrearAsistencia);
        Button btnEliminarAsistencias = findViewById(R.id.btnEliminarAsistencia);
        Button btnEditarAsistencias = findViewById(R.id.btnEditarAsistencia);

        btnAsistencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrudAsistenciaActivity.this, ListAsistenciaActivity.class);
                startActivity(intent);
            }
        });

        btnCrearAsistencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrudAsistenciaActivity.this, CreateAsistenciaActivity.class);
                startActivity(intent);
            }
        });

        btnEditarAsistencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrudAsistenciaActivity.this, UpdateAsistenciaActivity.class);
                startActivity(intent);
            }
        });

        btnEliminarAsistencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrudAsistenciaActivity.this, DeleteAsistenciaActivity.class);
                startActivity(intent);
            }
        });


    }
}