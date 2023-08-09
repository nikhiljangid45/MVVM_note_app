package com.example.roomdatabase.RoomClass;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class,version = 1)
public abstract class NoteDatabase extends RoomDatabase {

      public static NoteDatabase instance;
      public abstract NoteDao noteDao();
      public static NoteDatabase getInstance(Context context) {
          if (instance == null) {
              instance = Room.databaseBuilder(context.getApplicationContext(),
                              NoteDatabase.class, "note_database")
                      .fallbackToDestructiveMigration().build();
          }
          return instance;
      }
}
