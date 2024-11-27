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

import com.example.androidxfirebase.Data.dao.AsistenciaDao;
import com.example.androidxfirebase.Data.dao.EmpleadoDao;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class DeleteAsistenciaActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private AsistenciaDao asistenciaDao;
    private Button eliminar;
    private EditText editTextNumeroAsistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_asistencia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseFirestore.getInstance();
        asistenciaDao = new AsistenciaDao(db);

        eliminar = findViewById(R.id.btnEliminar);
        editTextNumeroAsistencia = findViewById(R.id.editTextNumeroAsistencia);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asistenciaDao.delete(editTextNumeroAsistencia.getText().toString(), new OnSuccessListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {

                    }
                });
                editTextNumeroAsistencia.setText("");
                Toast.makeText(DeleteAsistenciaActivity.this,"Asistencia eliminada",Toast.LENGTH_SHORT).show();
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