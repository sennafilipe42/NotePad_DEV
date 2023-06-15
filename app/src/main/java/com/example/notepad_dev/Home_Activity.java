package com.example.notepad_dev;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.notepad_dev.databinding.ActivityHomeBinding;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;

public class Home_Activity extends AppCompatActivity {

    ActivityHomeBinding binding;
    NoteAdapter noteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.toolbarHome.botaoLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            finish();
            abrirLogin();
        });
        binding.toolbarHome.botaoTema.setOnClickListener(v -> {
            finish();
            abrirTema();
        });

        binding.addNotePAD.setOnClickListener(v -> {
            finish();
            abrirNewNotePad();
        });

        setupRecyclerView();

    }

    void setupRecyclerView() {
        Query query = Utilidades.getCollectionReferenceDeNotepads().orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class).build();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(options, this);
        binding.recyclerView.setAdapter(noteAdapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        noteAdapter.startListening();

    }
    @Override
    protected void onStop(){
        super.onStop();
        noteAdapter.stopListening();

    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume(){
        super.onResume();
        noteAdapter.notifyDataSetChanged();

    }
    private void abrirLogin() {
        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
        startActivity(intent);
    }

    private void abrirTema() {
        Intent intent = new Intent(getApplicationContext(), Tema_Activity.class);
        startActivity(intent);
    }


    private void abrirNewNotePad() {
        Intent intent = new Intent(getApplicationContext(), NewNotePad_Activity.class);
        startActivity(intent);
    }
}
