package com.example.androidxfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidxfirebase.Data.dao.EmpleadoDao;
import com.example.androidxfirebase.Data.model.Empleado;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateEmpleado extends AppCompatActivity {

    private FirebaseFirestore db;
    private EmpleadoDao empleadoDao;
    private EditText editTextEmpleadoname,editTextEmail,editTextApellido,editTextEdad,editTextDireccion,editTextTelefono,editTextCargo,editTextFnacimiento,editTextFcontratacion,editTextEstado,editTextTipoDocumento,editTextDepartamento,editTextNumeroDocumento,editTextID;
    private Button buttonUpdateEmpleado,buttonUpdateImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_empleado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = FirebaseFirestore.getInstance();
        empleadoDao = new EmpleadoDao(db);

        buttonUpdateEmpleado = findViewById(R.id.buttonUpdateEmpleado);
        buttonUpdateImage = findViewById(R.id.buttonUpdateImage);
        editTextEmpleadoname = findViewById(R.id.editTextEmpleadoname);
        editTextID = findViewById(R.id.editTextID);
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

        buttonUpdateEmpleado.setOnClickListener(new View.OnClickListener() {
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
                String ID = editTextID.getText().toString();

                empleadoDao.update(ID,new Empleado(nombre,Apellido,edad,email,direccion,telefono,cargo,fnacimiento,fcontratacion,estado,tipo_documento,departamento,numero_documento,""),new OnSuccessListener<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {

                    }
                });
            }
        });

        buttonUpdateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String empleadoID = editTextID.getText().toString();

                // Crea el Intent y añade el texto como extra
                Intent intent = new Intent(UpdateEmpleado.this, UpdateImage.class);
                intent.putExtra("empleado_id", empleadoID); // "empleado_id" es la clave para recuperar el dato en UpdateEmpleado
                startActivity(intent);

            }

        });

}
}