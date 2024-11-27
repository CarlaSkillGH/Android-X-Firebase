package com.example.androidxfirebase.Data.dao;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.androidxfirebase.Data.model.Asistencia;
import com.example.androidxfirebase.Data.model.Empleado;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsistenciaDao {

    private static final String TAG = "AsistenciaDao";
    private static final String COLLECTION_NAME = "asistencia";

    private final FirebaseFirestore db;

    /**
     * Constructor de la clase UserDao.
     *
     * @param db Instancia de FirebaseFirestore para acceder a la base de datos.
     */
    public AsistenciaDao(FirebaseFirestore db) {
        this.db = db;
    }

    /**
     * Inserta un nuevo usuario en la colección "users".
     *
     * @param user      El objeto User a insertar.
     * @param listener Listener para notificar el resultado de la operación.
     */
    public void insert(Asistencia user, OnSuccessListener<String> listener) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("fecha_entrada", user.getFecha_entrada());
        userData.put("fecha_salida", user.getFecha_salida());
        userData.put("novedades_asistencia", user.getNovedades_asistencia());
        userData.put("numero_documento_identidad", user.getNumero_documento_identidad());

        db.collection(COLLECTION_NAME)
                .add(userData)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "onSuccess: " + documentReference.getId());
                    listener.onSuccess(documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "onFailure: ", e);
                    listener.onSuccess(null);
                });
    }

    /**
     * Actualiza los datos de un usuario existente.
     *
     * @param id       El ID del usuario a actualizar.
     * @param user      El objeto User con los datos actualizados.
     * @param listener Listener para notificar el resultado de la operación.
     */
    public void update(String id, Asistencia user, OnSuccessListener<Boolean> listener) {
        Map<String, Object> userData = new HashMap<>();
        userData.put("fecha_entrada", user.getFecha_entrada());
        userData.put("fecha_salida", user.getFecha_salida());
        userData.put("novedades_asistencia", user.getNovedades_asistencia());
        userData.put("numero_documento_identidad", user.getNumero_documento_identidad());

        db.collection(COLLECTION_NAME)
                .document(id)
                .update(userData)
                .addOnSuccessListener(unused -> listener.onSuccess(true))
                .addOnFailureListener(e -> {
                    Log.e(TAG, "onFailure: ", e);
                    listener.onSuccess(false);
                });
    }


    /**
     * Obtiene un usuario por su ID.
     *
     * @param id       El ID del usuario a obtener.
     * @param listener Listener para notificar el resultado de la operación.
     */
    public void getById(String id, OnSuccessListener<Asistencia> listener) {
        db.collection(COLLECTION_NAME)
                .document(id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Asistencia empleado = document.toObject(Asistencia.class);
                            listener.onSuccess(empleado);
                        } else {
                            listener.onSuccess(null);
                        }
                    } else {
                        Log.e(TAG, "onComplete: ", task.getException());
                        listener.onSuccess(null);
                    }
                });
    }

    /**
     * Obtiene todos los usuarios de la colección.
     *
     * @param listener Listener para notificar el resultado de la operación.
     */


    public void getAll(OnSuccessListener<List<Asistencia>> listener) {
        db.collection(COLLECTION_NAME).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<Asistencia> userList = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        Asistencia empleado = documentSnapshot.toObject(Asistencia.class);
                        userList.add(empleado);
                    }
                    listener.onSuccess(userList);
                } else {
                    listener.onSuccess(null);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure: ", e);
                listener.onSuccess(null);
            }
        });
    }


    /**
     * Elimina un usuario de la colección por su ID.
     *
     * @param id       El ID del usuario a eliminar.
     * @param listener Listener para notificar el resultado de la operación.
     */
    public void delete(String id, OnSuccessListener<Boolean> listener) {
        db.collection(COLLECTION_NAME)
                .document(id)
                .delete()
                .addOnSuccessListener(unused -> listener.onSuccess(true))
                .addOnFailureListener(e -> {
                    Log.e(TAG, "onFailure: ", e);
                    listener.onSuccess(false);
                });
    }

}
