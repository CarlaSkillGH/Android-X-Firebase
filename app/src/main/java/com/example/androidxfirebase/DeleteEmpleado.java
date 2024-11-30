package com.example.androidxfirebase;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidxfirebase.Data.dao.EmpleadoDao;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class DeleteEmpleado extends AppCompatActivity {

    private FirebaseFirestore db;
    private EmpleadoDao empleadoDao;
    private Button eliminar;
    private EditText editTextNumeroDocumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_empleado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseFirestore.getInstance();
        empleadoDao = new EmpleadoDao(db);

        eliminar = findViewById(R.id.btnEliminar);
        editTextNumeroDocumento = findViewById(R.id.editTextNumeroDocumento);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empleadoDao.delete(editTextNumeroDocumento.getText().toString(), new OnSuccessListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {

                    }
                });
                editTextNumeroDocumento.setText("");
                Toast.makeText(DeleteEmpleado.this,"Empleado eliminado",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.terminate();
        db.clearPersistence();
    }
}