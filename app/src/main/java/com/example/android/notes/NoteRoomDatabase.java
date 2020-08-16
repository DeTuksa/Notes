package com.example.android.notes;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteRoomDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static NoteRoomDatabase INSTANCE;

//    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//            new PopulateDbAsync(INSTANCE).execute();
//        }
//    };
//
//    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
//
//        private final NoteDao mDao;
//        ArrayList<Note> notes = new ArrayList<>();
//
//
//        PopulateDbAsync(NoteRoomDatabase db) {
//            mDao = db.noteDao();
//            notes.add(new Note("Lores ipsum dores sit amet Lores ipsum dores sit amet Lores ipsum dores sit amet ", "The dog", "8 jul 2020"));
//            notes.add(new Note("Lores ipsum dores sit amet Lores ipsum dores sit amet Lores ipsum dores sit amet ", "The dog", "8 jul 2020"));
//        }
//
//        @Override
//        protected Void doInBackground(final Void... params) {
//            // Start the app with a clean database every time.
//            // Not needed if you only populate the database
//            // when it is first created
//            mDao.deleteAll();
//
//            for (int i = 0; i <= notes.size() - 1; i++) {
//                Note word = notes.get(i);
//                mDao.insert(word);
//            }
//            return null;
//        }
//    }

    static NoteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoteRoomDatabase.class, "notes")
                            .fallbackToDestructiveMigration()
//                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

