package com.example.mahadandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView mytext = findViewById(R.id.textview);
        Button btn = findViewById(R.id.mybutton);
        EditText myedit = findViewById(R.id.myedittext);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}