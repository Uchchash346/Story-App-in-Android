package com.uchchash.storyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {
    TextView txt;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        txt = findViewById(R.id.nxt);
        String t = getIntent().getStringExtra("key");
        txt.setText(t);

    }
}