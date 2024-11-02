package com.example.androidxfirebase;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidxfirebase.Data.Home.Home;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
public class Authentication extends AppCompatActivity {


    private static final String TAG = "Authentication";
    private static final int REQ_ONE_TAP = 2;  // Puedes usar cualquier entero único

    private FirebaseAuth auth;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;
    private Button googleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_authentication);
        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Inicializar el cliente One Tap
        oneTapClient = Identity.getSignInClient(this);

        // Construir el request de inicio de sesión
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // ID de cliente del servidor, NO el de la aplicación Android
                        .setServerClientId(getString(R.string.default_web_client_id))
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                .build();

        // Inicializar vistas
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        googleButton = findViewById(R.id.oneTapSignInButton);

        // Configurar listeners de botones
        loginButton.setOnClickListener(v -> loginUser());
        registerButton.setOnClickListener(v -> registerUser());
        googleButton.setOnClickListener(v -> signIn());
    }
    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validaciones básicas
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mostrar progreso
        loginButton.setEnabled(false);

        // Intentar login
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    loginButton.setEnabled(true);
                    if (task.isSuccessful()) {
                        // Login exitoso
                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(Authentication.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                        // Aquí puedes redirigir al usuario a la siguiente actividad
                        Intent intent = new Intent(Authentication.this, Home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Login fallido
                        Toast.makeText(Authentication.this, "Error en el login: " +
                                task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void registerUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validaciones básicas
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar longitud de contraseña
        if (password.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // Mostrar progreso
        registerButton.setEnabled(false);

        // Intentar registro
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    registerButton.setEnabled(true);
                    if (task.isSuccessful()) {
                        // Registro exitoso
                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(Authentication.this, "Registro exitoso",
                                Toast.LENGTH_SHORT).show();
                        // Aquí puedes redirigir al usuario a la siguiente actividad
                        // Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        // startActivity(intent);
                        // finish();
                    } else {
                        // Registro fallido
                        Toast.makeText(Authentication.this, "Error en el registro: " +
                                task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Iniciar el flujo de inicio de sesión con Google One Tap
    private void signIn() {
        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(this, result -> {

                    try {
                        startIntentSenderForResult(
                                result.getPendingIntent().getIntentSender(), REQ_ONE_TAP,
                                null, 0, 0, 0);

                    } catch (IntentSender.SendIntentException e) {
                        Log.e(TAG, "Couldn't start One Tap UI: " + e.getLocalizedMessage());
                        // Si One Tap falla, iniciar el flujo normal de Google Sign-In
                    }
                })
                .addOnFailureListener(this, e -> {
                    // No se pudo mostrar la UI de One Tap, usar el flujo normal
                    Log.d(TAG, e.getLocalizedMessage());
                });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_ONE_TAP:
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();
                    if (idToken != null) {
                        // Autenticar con Firebase
                        firebaseAuthWithGoogle(idToken);
                    }

                } catch (ApiException e) {
                    // ... Manejar el error ...
                }
                break;

            // ... Manejar otros códigos de solicitud, como el del flujo normal ...
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(firebaseCredential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Inicio de sesión exitoso
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = auth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // Error de inicio de sesión
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // El usuario ha iniciado sesión
            // Redirigir a la actividad de inicio de sesión
            Intent intent = new Intent(Authentication.this, Home.class);
            startActivity(intent);
            finish();
        } else {
            // El usuario no ha iniciado sesión
            // Mostrar la interfaz de usuario de inicio de sesión
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Verificar si el usuario ya está logueado
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            // Usuario ya logueado, redirigir a la actividad principal
            Intent intent = new Intent(Authentication.this, Home.class);
            startActivity(intent);
            finish();
        }
    }
}