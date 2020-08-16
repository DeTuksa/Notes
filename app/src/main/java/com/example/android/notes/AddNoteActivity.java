package com.example.android.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class AddNoteActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = findViewById(R.id.newNoteToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        Intent i = getIntent();
        String id = i.getStringExtra("id");
        String title = i.getStringExtra("title");
        String text = i.getStringExtra("text");

        if (id != null && title != null && text != null) {
            note = new Note(text, title, "12th august", id);
            EditText titleText = findViewById(R.id.titleText);
            EditText contentText = findViewById(R.id.contentText);
            titleText.setText(title);
            contentText.setText(text);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addTag) {
            return true;
        }

        if (item.getItemId() == R.id.deleteNote) {
            if (this.note != null) {
                boolean success = noteViewModel.delete(note);
                if (success) {
                    finish();
                }
            }
            return true;
        }

        if (item.getItemId() == R.id.save) {
            EditText titleText = findViewById(R.id.titleText);
            EditText contentText = findViewById(R.id.contentText);
            String title = titleText.getText().toString();
            String content = contentText.getText().toString();
            Toast toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
            if (title.equals("")) {
                toast.setText("Empty title!");
            } else if (content.equals("")) {
                toast.setText("Empty Note!");
            } else {
                boolean success;
                if (this.note == null) {
                    UUID id = UUID.randomUUID();
                    Note note = new Note(content, title, "21st august", id.toString());
                    this.note = note;
                    success = noteViewModel.insert(note);
                } else {
                    this.note.setText(content);
                    this.note.setTitle(title);
                    this.note.setDate("21st august");
                    success = noteViewModel.update(this.note);
                }
                if (success) {
                    toast.setText("Saved!");
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    if (contentText.isFocused()) {
                        imm.hideSoftInputFromWindow(contentText.getWindowToken(), 0);
                        contentText.clearFocus();
                    }
                    if (titleText.isFocused()) {
                        imm.hideSoftInputFromWindow(titleText.getWindowToken(), 0);
                        titleText.clearFocus();
                    }
                } else {
                    toast.setText("Error Saving!");
                }
            }
            toast.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    public void onNoteClicked(View view) {
        TextView titleText = view.findViewById(R.id.titleText);
        TextView contentText = view.findViewById(R.id.contentText);
    }
}