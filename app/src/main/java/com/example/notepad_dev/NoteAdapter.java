package com.example.notepad_dev;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends FirestoreRecyclerAdapter<Note, NoteAdapter.NoteViewHolder> {
    Context context;

    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Note> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder holder, int position, @NonNull Note note) {
        // Associa os dados da nota aos elementos visuais do ViewHolder
        holder.bind(note);

        // Define o listener de clique para a visualização da nota
        holder.itemView.setOnClickListener(v -> {
            // Cria um intent para abrir a atividade NewNotePad_Activity
            Intent intent = new Intent(context, NewNotePad_Activity.class);
            // Passa os dados da nota para a atividade NewNotePad_Activity como extras
            intent.putExtra("titulo", note.titulo);
            intent.putExtra("texto", note.texto);
            // Obtém o ID do documento da nota no Firestore
            String docId = getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId", docId);
            // Inicia a atividade NewNotePad_Activity
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout do item de recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_home, parent, false);
        // Retorna uma instância de NoteViewHolder
        return new NoteViewHolder(view);
    }

    // Classe interna que representa o ViewHolder dos itens de recycler view
    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, contentTextView, timestampTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializa os elementos visuais do ViewHolder
            titleTextView = itemView.findViewById(R.id.tituloNotepad);
            contentTextView = itemView.findViewById(R.id.textoNotepad);
            timestampTextView = itemView.findViewById(R.id.timestampNotepad);
        }

        // Associa os dados da nota aos elementos visuais do ViewHolder
        public void bind(Note note) {
            titleTextView.setText(note.titulo);
            contentTextView.setText(note.texto);
            timestampTextView.setText(Utilidades.TimestampData(note.timestamp));
        }
    }
}
