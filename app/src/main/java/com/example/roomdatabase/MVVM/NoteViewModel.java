package com.example.roomdatabase.MVVM;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomdatabase.MVVM.NoteRepo;
import com.example.roomdatabase.RoomClass.Note;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

private NoteRepo noteRepo;
private LiveData<List<Note>> noteList;


    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepo = new NoteRepo(application);
        noteList = noteRepo.getAllData();
    }


    public void insert(Note note){
        noteRepo.insertData(note);
    }
    public void delet(Note note){
        noteRepo.deleteData(note);
    }
    public void update(Note note){
        noteRepo.updateData(note);
    }
    public LiveData<List<Note>> getAllData(){
        return noteList;
    }

}
