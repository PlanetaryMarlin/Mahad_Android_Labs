package com.example.mahadandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mahadandroidlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());
        setContentView(R.layout.activity_main);

        TextView mytext = variableBinding.textview;
        EditText myedit = variableBinding.myedittext;
        final Button btn = variableBinding.mybutton;

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String editString = myedit.getText().toString();
                mytext.setText( "Your edit text has: " + editString);
            }
        });


    }
}