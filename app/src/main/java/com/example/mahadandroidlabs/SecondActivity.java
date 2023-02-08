package com.example.mahadandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconds);



        Intent fromPrevious = getIntent();

        //Retrieve the data from the first page, and transform the text.
        String emailAddress = fromPrevious.getStringExtra("Email Address");
        String message = ("Welcome Back" emailAddress;


}