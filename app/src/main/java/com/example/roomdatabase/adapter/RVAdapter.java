package com.example.roomdatabase.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.RoomClass.Note;
import com.example.roomdatabase.R;
import com.example.roomdatabase.databinding.EachRvBinding;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    Context context;
    ArrayList<Note> noteArrayList;

    public RVAdapter(Context context, ArrayList<Note> noteArrayList) {
        this.context = context;
        this.noteArrayList = noteArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_rv,parent,false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Note note = noteArrayList.get(position);
        holder.binding.titleRv.setText(note.getTitle());
        holder.binding.disRv.setText(note.getDisp());
        int id = note.getId();
        if (id == 6){
            holder.binding.constraint.setBackgroundColor(R.color.newcolor6);
        }

        if (id == 7){
            holder.binding.constraint.setBackgroundColor(R.color.white);
        }
        if (id == 8){
            holder.binding.constraint.setBackgroundColor(R.color.black);
        }
        if (id == 9){
            holder.binding.constraint.setBackgroundColor(R.color.newcolor9);
        }
        if (id == 10){
            holder.binding.constraint.setBackgroundColor(R.color.newcolor10);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, " "+id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
           EachRvBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            binding =EachRvBinding.bind(itemView);

        }
    }
}
