package com.example.android.notes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(Application application) {
        super((application));
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public boolean insert(Note note) {
        repository.insert(note);
        return true;
    }

    public boolean update(Note note) {
        repository.update(note);
        return true;
    }

    public boolean delete(Note note) {
        repository.delete(note);
        return true;
    }
}
