package com.example.roomdatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.roomdatabase.MVVM.NoteViewModel;
import com.example.roomdatabase.RoomClass.Note;
import com.example.roomdatabase.adapter.RVAdapter;
import com.example.roomdatabase.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    private NoteViewModel noteViewModel;
    RVAdapter adapter;


    ArrayList<Note> noteArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        adapter= new RVAdapter(getApplicationContext(),noteArrayList);

        noteViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(NoteViewModel.class);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DataInsertActivity.class);
                intent.putExtra("type","addMode");
                startActivityForResult(intent,1);
            }
        });



        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleView.setHasFixedSize(true);



        noteViewModel.getAllData().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteArrayList = (ArrayList<Note>) notes;
                RVAdapter adapter= new RVAdapter(getApplicationContext(),noteArrayList);
                binding.recycleView.setAdapter(adapter);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


                if (direction == ItemTouchHelper.RIGHT){
                    Note note = noteArrayList.get(viewHolder.getAdapterPosition());
                    noteViewModel.delet(note);
                }else {
                    Intent intent = new Intent(MainActivity.this,DataInsertActivity.class);

                    int position = viewHolder.getAdapterPosition();
                    if (position >= 0 && position < noteArrayList.size()) {
                        // Get the swiped contact and remove it from the list
                        Note swipedContact = noteArrayList.get(position);
                        intent.putExtra("type","update");
                        intent.putExtra("title",swipedContact.getTitle());
                        intent.putExtra("disp",swipedContact.getDisp());
                        intent.putExtra("id",swipedContact.getId());
                        startActivityForResult(intent,2);

                        Toast.makeText(MainActivity.this, "Update Data", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "sdjbfksdbfsdf", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        }).attachToRecyclerView(binding.recycleView);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            String title = data.getStringExtra("title");
            String disp = data.getStringExtra("disp");
            Note note = new Note(title,disp);
            noteViewModel.insert(note);
            Toast.makeText(this, "note Added", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == 2){
            String title = data.getStringExtra("title");
            String disp = data.getStringExtra("disp");
           Note note = new Note(title,disp);
           note.setId(data.getIntExtra("id",0));
           noteViewModel.update(note);
            Toast.makeText(this, "note Update", Toast.LENGTH_SHORT).show();
        }
    }
}