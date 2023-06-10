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

    static void showToast(Context activity, String mensagem){
        Toast.makeText(activity,mensagem,Toast.LENGTH_LONG).show();
    }

    static CollectionReference getCollectionReferenceDeNotepads(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("notepads").document(currentUser.getUid()).collection("meus_notepads");
    }

    static String TimestampData(Timestamp timestamp){
        return new SimpleDateFormat("dd/MM/yyyy").format(timestamp.toDate());

    }
}
