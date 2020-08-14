package com.example.android.notes;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class NotePreviewAdapter extends RecyclerView.Adapter<NotePreviewAdapter.WordViewHolder> {
    private LayoutInflater mInflater;
    private final ArrayList<NotePreview> previews;

    @NonNull
    @Override
    public NotePreviewAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.preview_item,
                parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NotePreviewAdapter.WordViewHolder holder, int position) {
        NotePreview currentPreview = previews.get(position);
        holder.snippet.setText(currentPreview.getPreviewText());
        holder.date.setText(currentPreview.getDate());
    }

    @Override
    public int getItemCount() {
        return previews.size();
    }

    public NotePreviewAdapter(Context context,
                              ArrayList<NotePreview> previews) {
        mInflater = LayoutInflater.from(context);
        this.previews = previews;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView snippet;
        public final TextView date;
        final NotePreviewAdapter mAdapter;

        public WordViewHolder(View itemView, NotePreviewAdapter adapter) {
            super(itemView);
            snippet = itemView.findViewById(R.id.snippet);
            date = itemView.findViewById(R.id.date);
            this.mAdapter = adapter;
        }

    }

}
