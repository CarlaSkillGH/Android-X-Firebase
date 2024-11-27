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

public class UpdateAsistenciaActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private AsistenciaDao asistenciaDao;
    private EditText editidentidadText,editEntradaText,editSalidaText,editNovedadesText,editIDText;
    private Button buttonUpdateAsistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_asistencia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        db = FirebaseFirestore.getInstance();
        asistenciaDao = new AsistenciaDao(db);

        buttonUpdateAsistencia = findViewById(R.id.buttonUpdateAsistencia);
        editidentidadText = findViewById(R.id.editTextIdentidad);
        editEntradaText = findViewById(R.id.editTextEntrada);
        editSalidaText = findViewById(R.id.editTextSalida);
        editNovedadesText = findViewById(R.id.editTextNovedades);
        editIDText = findViewById(R.id.editTextID);


        buttonUpdateAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String identidad = editidentidadText.getText().toString();
                String entrada = editEntradaText.getText().toString();
                String salida = editSalidaText.getText().toString();
                String novedades = editNovedadesText.getText().toString();
                String ID = editIDText.getText().toString();


                asistenciaDao.update(ID,new Asistencia(entrada,salida,novedades,identidad),new OnSuccessListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {

                    }
                });
                editidentidadText.setText("");
                editEntradaText.setText("");
                editSalidaText.setText("");
                editNovedadesText.setText("");
                editIDText.setText("");

                Toast.makeText(UpdateAsistenciaActivity.this,"Empleado actualizado",Toast.LENGTH_SHORT).show();
            }

        });
    }
}