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
import com.example.androidxfirebase.Data.model.Asistencia;
import com.example.androidxfirebase.Data.model.Empleado;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateAsistenciaActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private AsistenciaDao asistenciaDao;
    private EditText editIDText,editEntradaText,editSalidaText,editNovedadesText;
    private Button regresar,crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_asistencia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        db = FirebaseFirestore.getInstance();
        asistenciaDao = new AsistenciaDao(db);

        regresar = findViewById(R.id.buttonReturnU);
        crear = findViewById(R.id.buttonCreateEmpleado);
        editIDText = findViewById(R.id.editTextIdentidad);
        editEntradaText = findViewById(R.id.editTextEntrada);
        editSalidaText = findViewById(R.id.editTextSalida);
        editNovedadesText = findViewById(R.id.editTextNovedades);


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual
            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String identidad = editIDText.getText().toString();
                String entrada = editEntradaText.getText().toString();
                String salida = editSalidaText.getText().toString();
                String novedades = editNovedadesText.getText().toString();


                asistenciaDao.insert(new Asistencia(entrada,salida,novedades,identidad), new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {

                    }
                });
                editIDText.setText("");
                editEntradaText.setText("");
                editSalidaText.setText("");
                editNovedadesText.setText("");
                Toast.makeText(CreateAsistenciaActivity.this,"Asistencis creada",Toast.LENGTH_SHORT).show();
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