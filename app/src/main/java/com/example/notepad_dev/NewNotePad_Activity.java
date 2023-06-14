package com.example.notepad_dev;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notepad_dev.databinding.ActivityHomeBinding;
import com.example.notepad_dev.databinding.ActivityNewNotePadBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;

public class NewNotePad_Activity extends AppCompatActivity {
    ActivityNewNotePadBinding binding;
    String titulo, texto, docId;
    boolean modoEdicao = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note_pad);
        binding = ActivityNewNotePadBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(R.layout.activity_new_note_pad);
        setContentView(view);

        binding.toolbarNewNotePad.botaoInfoCriadorAPP.setOnClickListener(v->{
            abrirInfo();
            finish();
        });

        binding.toolbarNewNotePad.botaoWallpapers.setOnClickListener(v->{
            abrirWallpapers();
            finish();
        });

        binding.toolbarNewNotePad.botaoLogout.setOnClickListener(v->{
            FirebaseAuth.getInstance().signOut();
            finish();
            abrirLogin();
        });


        titulo = getIntent().getStringExtra("titulo");
        texto = getIntent().getStringExtra("texto");
        docId = getIntent().getStringExtra("docId");

        if(docId!=null && !docId.isEmpty()){
            modoEdicao = true;
        }

        binding.notePadTitulo.setText(titulo);
        binding.notePadTexto.setText(texto);

        if (modoEdicao){
            binding.tituloPagina.setText("Edite o seu NotePad");
        }




        binding.botaoSalvarNotePad.setOnClickListener(v->{
            salvarNotePad();
        });

    }

    void salvarNotePad(){
        String titulo = binding.notePadTitulo.getText().toString();
        String texto = binding.notePadTexto.getText().toString();

        if (titulo.isEmpty()){
            binding.notePadTitulo.setError("Necessário inserir um titulo.");
            return;
        }
        Note note = new Note();
        note.setTitulo(titulo);
        note.setTexto(texto);
        note.setTimestamp(Timestamp.now());

        salvarNoteFirebase(note);
    }

    void salvarNoteFirebase(Note note){
        DocumentReference documentReference;

        if(modoEdicao){
            documentReference = Utilidades.getCollectionReferenceDeNotepads().document(docId);

        }else{
            documentReference = Utilidades.getCollectionReferenceDeNotepads().document();
        }

        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    if(modoEdicao){
                        Utilidades.showToast(NewNotePad_Activity.this, "Notepad Editado com sucesso!");
                        finish();
                        abrirHome();
                    }else{
                        Utilidades.showToast(NewNotePad_Activity.this, "Notepad Adicionado com sucesso!");
                        finish();
                        abrirHome();
                    }


                }else {
                    Utilidades.showToast(NewNotePad_Activity.this, "Falha ao salvar o Notepad.");

                }
            }
        });
    }
    private void abrirHome(){
        Intent intent = new Intent(getApplicationContext(), Home_Activity.class );
        startActivity(intent);
    }

    private void abrirLogin() {
        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
        startActivity(intent);
    }

    public void abrirWallpapers() {
        Intent intent = new Intent(getApplicationContext(), Wallpapers_Activity.class);
        startActivity(intent);
    }

    public void abrirInfo() {
        Intent intent = new Intent(getApplicationContext(), Info__Activity.class);
        startActivity(intent);
    }
}