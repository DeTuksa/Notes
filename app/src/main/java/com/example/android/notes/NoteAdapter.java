package com.example.android.notes;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private final LayoutInflater inflater;
    private List<Note> notes;
    private Context context;

    NoteAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.preview_item, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        final Note current = notes.get(position);
        if (notes != null) {
            holder.snippet.setText(current.getText());
            holder.date.setText(current.getDate());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.v("HERE: ", "PLEASE WORK");
                    Intent i = new Intent(context, AddNoteActivity.class);
                    i.putExtra("id", current.getId());
                    i.putExtra("title", current.getTitle());
                    i.putExtra("text", current.getText());
                    view.getContext().startActivity(i);
                }
            });
        } else {
            holder.snippet.setText("No text");
            holder.date.setText("No date");
        }
    }

    void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (notes != null) {
            return notes.size();
        } else {
            return 0;
        }
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        public final TextView snippet;
        public final TextView date;
        public final View itemView;

        public NoteViewHolder(View itemView) {
            super(itemView);
            snippet = itemView.findViewById(R.id.snippet);
            date = itemView.findViewById(R.id.date);
            this.itemView = itemView;
        }

    }
}
