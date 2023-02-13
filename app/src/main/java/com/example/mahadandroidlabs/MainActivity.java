package com.example.mahadandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );

        Button loginButton = findViewById(R.id.loginButton);
        EditText emailEditText = findViewById(R.id.emailEditText);

        loginButton.setOnClickListener( clk-> {
            Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);
            nextPage.putExtra( "Email Address", emailEditText.getText().toString() );
            startActivity(nextPage);
        } );


    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.w( "MainActivity", "In onDestroy() - Loading Widgets" );
    }

    @Override
    protected void onPause() {

        super.onPause();
        Log.w( "MainActivity", "In onPause() - Loading Widgets" );
    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.w( "MainActivity", "In onResume() - Loading Widgets" );
    }

    @Override
    protected void onStop() {

        super.onStop();
        Log.w( "MainActivity", "In onStop() - Loading Widgets" );
    }

    @Override
    protected void onStart() {

        super.onStart();
        Log.w( "MainActivity", "In onStart() - Loading Widgets" );
    }
}