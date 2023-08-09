package com.example.roomdatabase;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomdatabase.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {

    ActivityDataInsertBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String type = getIntent().getStringExtra("type");
        if (type.equals("update")){
            binding.button.setText(type);

            binding.title.setText(getIntent().getStringExtra("title"));
            binding.disp.setText(getIntent().getStringExtra("disp"));

            int id = getIntent().getIntExtra("id",0);
            binding.button.setOnClickListener(v -> {
                Intent intent = new Intent(DataInsertActivity.this,MainActivity.class);
                intent.putExtra("title",binding.title.getText().toString().trim());
                intent.putExtra("disp",binding.disp.getText().toString().trim());
                intent.putExtra("id",id);
                setResult(RESULT_OK,intent);
                finish();
            });
        }else {
            binding.button.setOnClickListener(v -> {
                Intent intent = new Intent(DataInsertActivity.this,MainActivity.class);
                intent.putExtra("title",binding.title.getText().toString().trim());
                intent.putExtra("disp",binding.disp.getText().toString().trim());
                setResult(RESULT_OK,intent);
                finish();
            });


        }


    }

    @Override
    public void onBackPressed() {
         super.onBackPressed();
     startActivity(new Intent(DataInsertActivity.this,MainActivity.class));
     finish();
    }

}