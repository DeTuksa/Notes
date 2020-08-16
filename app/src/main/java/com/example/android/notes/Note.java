package com.example.android.notes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "noteId")
    private String id;

    @NonNull
    @ColumnInfo(name = "text")
    private String text;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    public Note(@NonNull String text, @NonNull String title, @NonNull String date, @NonNull String id) {
        this.text = text;
        this.title = title;
        this.date = date;
        this.id = id;
    }

    @NonNull
    public String getText() {
        return text;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }
}
