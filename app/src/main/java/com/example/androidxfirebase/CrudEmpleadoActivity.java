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

import com.example.androidxfirebase.Data.Home.Home;

public class CrudEmpleadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crud_empleado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnEmpleados = findViewById(R.id.btnEmpleados);
        Button btnCrearUsuarios = findViewById(R.id.btnCrearEmpleados);
        Button btnEliminarEmpleado = findViewById(R.id.btnEliminarEmpleado);
        Button btnEditarUsuarios = findViewById(R.id.btnEditar);

        btnEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrudEmpleadoActivity.this, ListEmpleadoActivity.class);
                startActivity(intent);
            }
        });

        btnCrearUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrudEmpleadoActivity.this, CreateEmpleadoActivity.class);
                startActivity(intent);
            }
        });

        btnEditarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrudEmpleadoActivity.this, UpdateEmpleado.class);
                startActivity(intent);
            }
        });

        btnEliminarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrudEmpleadoActivity.this, DeleteEmpleado.class);
                startActivity(intent);
            }
        });

    }
}