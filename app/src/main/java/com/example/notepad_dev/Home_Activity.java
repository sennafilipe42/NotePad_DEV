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

        // Inflar o layout da atividade
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Configurar o clique do botão de logout na barra de ferramentas
        binding.toolbarHome.botaoLogout.setOnClickListener(v -> {

            // Efetuar logout do usuário atual
            FirebaseAuth.getInstance().signOut();
            finish();
            abrirLogin();
        });

        // Configurar o clique do botão de tema na barra de ferramentas
        binding.toolbarHome.botaoTema.setOnClickListener(v -> {
            finish();
            abrirTema();
        });

        // Configurar o clique do botão para adicionar uma nova nota
        binding.addNotePAD.setOnClickListener(v -> {
            finish();
            abrirNewNotePad();
        });

        // Configurar o RecyclerView
        setupRecyclerView();

    }

    // Configurar o RecyclerView e o adaptador
    void setupRecyclerView() {

        // Consulta para obter as notas ordenadas por timestamp em ordem decrescente
        Query query = Utilidades.getCollectionReferenceDeNotepads().orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class).build();

        // Configurar o layout manager e o adaptador do RecyclerView
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(options, this);
        binding.recyclerView.setAdapter(noteAdapter);
    }

    @Override
    protected void onStart(){
        super.onStart();

        // Iniciar a escuta do adaptador do RecyclerView
        noteAdapter.startListening();

    }
    @Override
    protected void onStop(){
        super.onStop();

        // Parar a escuta do adaptador do RecyclerView
        noteAdapter.stopListening();

    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume(){
        super.onResume();

        // Notificar o adaptador sobre as alterações de dados (por exemplo, ao retornar de uma atividade)
        noteAdapter.notifyDataSetChanged();

    }

    // Método para abrir a atividade de login
    private void abrirLogin() {
        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
        startActivity(intent);
    }

    // Método para abrir a atividade de tema
    private void abrirTema() {
        Intent intent = new Intent(getApplicationContext(), Tema_Activity.class);
        startActivity(intent);
    }

    // Método para abrir a atividade de criação de nova nota
    private void abrirNewNotePad() {
        Intent intent = new Intent(getApplicationContext(), NewNotePad_Activity.class);
        startActivity(intent);
    }
}
