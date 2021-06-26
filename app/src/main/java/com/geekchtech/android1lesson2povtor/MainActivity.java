package com.geekchtech.android1lesson2povtor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter adapter;
    FloatingActionButton fab;
    private ArrayList<Students> list = new ArrayList<>();
    private int pos;
    private String name, surname;
    private Students students;
    private boolean isChanged = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        fab = findViewById(R.id.fab);
        adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> {
         Intent intent = new Intent(this, MainActivity2.class);
         isChanged = false;
         startActivityForResult(intent, 100);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        students = new Students(data.getStringExtra("name"), data.getStringExtra("surname"));
        if (isChanged) {
            adapter.changeStudent(students, pos);
        }else {
            adapter.addStudent(students);
        }
        adapter.onRecyclerViewClickListener(new MainAdapter.onRecyclerViewClickListener() {
            @Override
            public void onItemClick(Students students, int position) {
                pos = position;
                isChanged = true;
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("naame", data.getStringExtra("name"));
                intent.putExtra("surrname", data.getStringExtra("surname"));
                Log.e("TAG", "MainClick" + pos);
                startActivityForResult(intent, 200);
            }
        });



    }



}