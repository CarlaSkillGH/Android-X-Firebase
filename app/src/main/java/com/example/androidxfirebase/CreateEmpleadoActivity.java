package com.example.androidxfirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidxfirebase.Data.adapter.EmpleadoAdapter;
import com.example.androidxfirebase.Data.dao.EmpleadoDao;
import com.example.androidxfirebase.Data.model.Empleado;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class  CreateEmpleadoActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EmpleadoDao empleadoDao;
    private EditText editTextEmpleadoname,editTextEmail,editTextApellido,editTextEdad,editTextDireccion,editTextTelefono,editTextCargo,editTextFnacimiento,editTextFcontratacion,editTextEstado,editTextTipoDocumento,editTextDepartamento,editTextNumeroDocumento;
    private Button regresar,crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_empleado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseFirestore.getInstance();
        empleadoDao = new EmpleadoDao(db);

        regresar = findViewById(R.id.buttonReturnU);
        crear = findViewById(R.id.buttonCreateEmpleado);
        editTextEmpleadoname = findViewById(R.id.editTextEmpleadoname);
        editTextApellido = findViewById(R.id.editTextApellido);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEdad = findViewById(R.id.editTextEdad);
        editTextDireccion = findViewById(R.id.editTextDireccion);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextCargo = findViewById(R.id.editTextCargo);
        editTextFnacimiento = findViewById(R.id.editTextFnacimiento);
        editTextFcontratacion = findViewById(R.id.editTextFcontratacion);
        editTextEstado = findViewById(R.id.editTextEstado);
        editTextTipoDocumento = findViewById(R.id.editTextTipoDocumento);
        editTextDepartamento = findViewById(R.id.editTextDepartamento);
        editTextNumeroDocumento = findViewById(R.id.editTextNumeroDocumento);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual
            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = editTextEmpleadoname.getText().toString();
                String Apellido = editTextApellido.getText().toString();
                String edad = editTextEdad.getText().toString();
                String email = editTextEmail.getText().toString();
                String direccion = editTextDireccion.getText().toString();
                String telefono = editTextTelefono.getText().toString();
                String cargo = editTextCargo.getText().toString();
                String fnacimiento = editTextFnacimiento.getText().toString();
                String fcontratacion = editTextFcontratacion.getText().toString();
                String estado = editTextEstado.getText().toString();
                String tipo_documento = editTextTipoDocumento.getText().toString();
                String departamento = editTextDepartamento.getText().toString();
                String numero_documento = editTextNumeroDocumento.getText().toString();

                empleadoDao.insert(new Empleado(nombre,Apellido,numero_documento,fnacimiento,fcontratacion,estado,edad,direccion,departamento,cargo,email,"",telefono,tipo_documento), new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {

                    }
                });
                editTextEmpleadoname.setText("");
                editTextApellido.setText("");
                editTextEdad.setText("");
                editTextEmail.setText("");
                editTextDireccion.setText("");
                editTextTelefono.setText("");
                editTextCargo.setText("");
                editTextFnacimiento.setText("");
                editTextFcontratacion.setText("");
                editTextEstado.setText("");
                editTextTipoDocumento.setText("");
                editTextDepartamento.setText("");
                editTextNumeroDocumento.setText("");
                Toast.makeText(CreateEmpleadoActivity.this,"Empleado creado",Toast.LENGTH_SHORT).show();
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