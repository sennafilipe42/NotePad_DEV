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
        holder.bind(note);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NewNotePad_Activity.class);
            intent.putExtra("titulo",note.titulo);
            intent.putExtra("texto",note.texto);
            String docId = getSnapshots().getSnapshot(position).getId();
            intent.putExtra("docId", docId);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_home, parent, false);
        return new NoteViewHolder(view);
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, contentTextView, timestampTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tituloNotepad);
            contentTextView = itemView.findViewById(R.id.textoNotepad);
            timestampTextView = itemView.findViewById(R.id.timestampNotepad);
        }

        public void bind(Note note) {
            titleTextView.setText(note.titulo);
            contentTextView.setText(note.texto);
            timestampTextView.setText(Utilidades.TimestampData(note.timestamp));
        }
    }
}
