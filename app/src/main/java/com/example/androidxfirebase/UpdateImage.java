package com.example.androidxfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidxfirebase.Data.dao.EmpleadoDao;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateImage extends AppCompatActivity {

    private FirebaseFirestore db;
    private EmpleadoDao empleadoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_image);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseFirestore.getInstance();
        empleadoDao = new EmpleadoDao(db);

        Intent intent = getIntent();
        String empleaID = intent.getStringExtra("empleado_id");

        // Register a photo picker activity launcher for a single image
        ActivityResultLauncher<PickVisualMediaRequest> pickSingleMedia = registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: " + uri);
                // Obtén el Intent y el extra que contiene el nombre del empleado

                empleadoDao.updateImage(empleaID, uri, new OnSuccessListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {

                    }
                });
            } else {
                Log.d("PhotoPicker", "No media selected");
            }
        });

        // Launch the photo picker when needed (e.g., button click)
        pickSingleMedia.launch(new PickVisualMediaRequest.Builder().setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE).build());

        Button btnEmpleados = findViewById(R.id.btnEmpleados);
        Button btnCrearUsuarios = findViewById(R.id.btnCrearEmpleados);
        Button btnEliminarEmpleado = findViewById(R.id.btnEliminarEmpleado);
        Button btnEditarUsuarios = findViewById(R.id.btnEditar);

        btnEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateImage.this, ListEmpleadoActivity.class);
                startActivity(intent);
            }
        });

        btnCrearUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateImage.this, CreateEmpleadoActivity.class);
                startActivity(intent);
            }
        });

        btnEditarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateImage.this, UpdateEmpleado.class);
                startActivity(intent);
            }
        });

        btnEliminarEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateImage.this, DeleteEmpleado.class);
                startActivity(intent);
            }
        });
    }

}