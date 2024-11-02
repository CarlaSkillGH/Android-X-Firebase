package com.example.androidxfirebase.Data.Home;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.androidxfirebase.Authentication;
import com.example.androidxfirebase.R;
import com.google.firebase.auth.FirebaseAuth;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
    private FirebaseAuth auth;
    private TextView welcomeTextView;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Verificar si el usuario está autenticado
        if (auth.getCurrentUser() == null) {
            // Si no hay usuario autenticado, regresar al login
            Intent intent = new Intent(Home.this, Authentication.class);
            startActivity(intent);
            finish();
            return;
        }

        // Inicializar vistas
        welcomeTextView = findViewById(R.id.welcomeTextView);
        logoutButton = findViewById(R.id.logoutButton);

        // Mostrar información del usuario
        FirebaseUser user = auth.getCurrentUser();
        welcomeTextView.setText("Bienvenido: " + user.getEmail());

        // Configurar botón de logout
        logoutButton.setOnClickListener(v -> logout());
    }

    private void logout() {
        auth.signOut();
        // Regresar a la pantalla de login
        Intent intent = new Intent(Home.this, Authentication.class);
        // Limpiar el stack de activities
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Verificar usuario cada vez que la activity se inicie
        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(Home.this, Authentication.class);
            startActivity(intent);
            finish();
        }
    }
}
