package com.example.ujisoccer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class StandingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standings);
        TextView texto= findViewById(R.id.textView3);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        Log.d("myTag", "ID:"+id);

    }
}
