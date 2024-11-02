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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnEmpleados = findViewById(R.id.btnEmpleados);
        Button btnCrearUsuarios = findViewById(R.id.btnCrearEmpleados);
        Button btnEliminarEmpleado = findViewById(R.id.btnEliminarEmpleado);
        Button btnEditarUsuarios = findViewById(R.id.btnEditar);
        Button btnAuthentication = findViewById(R.id.btnAuthentication);

        btnEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListEmpleadoActivity.class);
                startActivity(intent);
            }
        });

        btnCrearUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateEmpleadoActivity.class);
                startActivity(intent);
            }
        });

        btnEditarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateEmpleado.class);
                startActivity(intent);
            }
        });

        btnEliminarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DeleteEmpleado.class);
                startActivity(intent);
            }
        });

        btnAuthentication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Authentication.class);
                startActivity(intent);
            }
        });

    }
}