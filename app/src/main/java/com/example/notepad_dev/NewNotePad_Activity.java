package com.example.notepad_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notepad_dev.databinding.ActivityNewNotePadBinding;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;

public class NewNotePad_Activity extends AppCompatActivity {
    ActivityNewNotePadBinding binding;
    String titulo, texto, docId;
    boolean modoEdicao = false;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note_pad);
        binding = ActivityNewNotePadBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(R.layout.activity_new_note_pad);
        setContentView(view);

        // Definir listener de clique para o botão Home na barra de ferramentas
        binding.toolbarNewNotePad.botaoHome.setOnClickListener(v -> {
            abrirHome();
            finish();
        });

        // Definir listener de clique para o botão Tema na barra de ferramentas
        binding.toolbarNewNotePad.botaoTema.setOnClickListener(v->{
            abrirTema();
            finish();
        });

        // Definir listener de clique para o botão Logout na barra de ferramentas
        binding.toolbarNewNotePad.botaoLogout.setOnClickListener(v->{
            FirebaseAuth.getInstance().signOut();
            finish();
            abrirLogin();
        });

        // Obter os dados da nota passados como extras
        titulo = getIntent().getStringExtra("titulo");
        texto = getIntent().getStringExtra("texto");
        docId = getIntent().getStringExtra("docId");

        // Verificar se é um modo de edição (se há um docId) e atualizar a visibilidade do botão de remoção
        if(docId!=null && !docId.isEmpty()){
            modoEdicao = true;
            binding.botaoRemoverNotepad.setVisibility(View.VISIBLE);
        }

        // Exibir os dados da nota nos campos de texto
        binding.notePadTitulo.setText(titulo);
        binding.notePadTexto.setText(texto);

        // Verificar se é um modo de edição e atualizar o texto da página
        if (modoEdicao){
            binding.tituloPagina.setText("Edite o seu NotePad");
        }

        // Definir listener de clique para o botão Salvar
        binding.botaoSalvarNotePad.setOnClickListener(v-> salvarNotePad());

        // Definir listener de clique para o botão Remover
        binding.botaoRemoverNotepad.setOnClickListener(v-> removerNotepadFirebase());

    }

    // Método para salvar a nota
    void salvarNotePad(){
        String titulo = binding.notePadTitulo.getText().toString();
        String texto = binding.notePadTexto.getText().toString();

        if (titulo.isEmpty()){
            binding.notePadTitulo.setError("Necessário inserir um titulo.");
            return;
        }

        // Criar uma instância de Note com os dados da nota
        Note note = new Note();
        note.setTitulo(titulo);
        note.setTexto(texto);
        note.setTimestamp(Timestamp.now());

        // Salvar a nota no Firebase Firestore
        salvarNoteFirebase(note);
    }

    // Método para salvar a nota no Firebase Firestore
    void salvarNoteFirebase(Note note){
        DocumentReference documentReference;

        if(modoEdicao){
            documentReference = Utilidades.getCollectionReferenceDeNotepads().document(docId);

        }else{
            documentReference = Utilidades.getCollectionReferenceDeNotepads().document();
        }

        documentReference.set(note).addOnCompleteListener(task -> {
            if (task.isSuccessful()){

                // Se a operação for bem-sucedida, exibir uma mensagem de sucesso e abrir a tela inicial
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

                // Se a operação falhar, exibir uma mensagem de falha
                Utilidades.showToast(NewNotePad_Activity.this, "Falha ao salvar o Notepad.");

            }
        });
    }

    // Método para remover o Notepad do Firebase Firestore
    void removerNotepadFirebase(){
        DocumentReference documentReference;
        documentReference = Utilidades.getCollectionReferenceDeNotepads().document(docId);
        documentReference.delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()){

                // Se a operação for bem-sucedida, exibir uma mensagem de sucesso e abrir a tela inicial
                Utilidades.showToast(NewNotePad_Activity.this, "Notepad removido com sucesso!");
                finish();
                abrirHome();
            }else {

                // Se a operação falhar, exibir uma mensagem de falha
                Utilidades.showToast(NewNotePad_Activity.this, "Falha ao remover o Notepad.");

            }
        });
    }

    // Método para abrir a tela inicial
    private void abrirHome(){
        Intent intent = new Intent(getApplicationContext(), Home_Activity.class );
        startActivity(intent);
    }

    // Método para abrir a tela de login
    private void abrirLogin() {
        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
        startActivity(intent);
    }

    // Método para abrir a tela de temas
    public void abrirTema() {
        Intent intent = new Intent(getApplicationContext(), Tema_Activity.class);
        startActivity(intent);
    }


}