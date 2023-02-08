package com.example.mahadandroidlabs;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );

        //loginButton.setOnClickListener( clk-> { } );
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