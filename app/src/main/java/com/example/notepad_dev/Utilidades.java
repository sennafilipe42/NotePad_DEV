package com.example.notepad_dev;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class Utilidades {

    // Método para exibir um Toast na tela
    static void showToast(Context activity, String mensagem){
        Toast.makeText(activity, mensagem, Toast.LENGTH_LONG).show();
    }

    // Método para obter a referência da coleção "notepads" no Firestore
    static CollectionReference getCollectionReferenceDeNotepads(){
        // Obtém o usuário atualmente autenticado
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        // Retorna a referência da coleção "notepads/meus_notepads" para o usuário atual
        return FirebaseFirestore.getInstance().collection("notepads").document(currentUser.getUid()).collection("meus_notepads");
    }

    // Método para formatar um Timestamp para uma data no formato "dd/MM/yyyy"
    static String TimestampData(Timestamp timestamp){
        return new SimpleDateFormat("dd/MM/yyyy").format(timestamp.toDate());
    }
}
