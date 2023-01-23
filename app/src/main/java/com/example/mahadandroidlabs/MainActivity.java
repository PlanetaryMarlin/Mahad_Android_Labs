package com.example.mahadandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView mytext = findViewById(R.id.textview);
        EditText myedit = findViewById(R.id.myedittext);
        final Button btn = findViewById(R.id.mybutton);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String editString = myedit.getText().toString();
                mytext.setText( "Your edit text has: " + editString);
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}