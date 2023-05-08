package com.uchchash.storyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    List<String> title_lt, storylt;
    ArrayAdapter<String> adapter;
    MyStory myStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        databaseReference = FirebaseDatabase.getInstance().getReference("storyBook");
        myStory = new MyStory();
        title_lt = new ArrayList<>();
        storylt = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,R.layout.demo, R.id.item_txt, title_lt);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                title_lt.clear();
                storylt.clear();
                for(DataSnapshot ds: snapshot.getChildren()){
                    myStory = ds.getValue(MyStory.class);
                    if(myStory != null){
                        title_lt.add(myStory.getTitle());
                    }
                    if(myStory != null){
                        storylt.add(myStory.getStory());
                    }
                }
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, NextActivity.class);
                        String p = storylt.get(position);
                        intent.putExtra("key", p);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}