package com.example.mahadandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = findViewById(R.id.loginButton);
        EditText emailEditText = findViewById(R.id.emailEditText);


        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        String emailAddress = prefs.getString("LoginName", "");
        emailEditText.setText(emailAddress);


        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );




        loginButton.setOnClickListener( clk-> {
            Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);
            nextPage.putExtra( "Email Address", emailEditText.getText().toString() );
            //Saves the email after the login button is pressed in the Shared Prefeerence folder.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("LoginName",  emailEditText.getText().toString());
            editor.apply();
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