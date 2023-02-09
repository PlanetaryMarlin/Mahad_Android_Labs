package com.example.mahadandroidlabs;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconds);

        Button callButton = findViewById(R.id.callButton);
        EditText phoneNumberText = findViewById(R.id.phoneNumberText);
        ImageView picture = findViewById(R.id.picture);

        Intent fromPrevious = getIntent();

        //Retrieve the data from the first page, and transform the text.
        String emailAddress = fromPrevious.getStringExtra("Email Address");
        String message = "Welcome Back" + emailAddress;

        callButton.setOnClickListener( clk-> {
            phoneNumberText.getText();
        });

        picture.setOnClickListener( clk-> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        });




        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {

                            Intent data = result.getData();

                        }

                    }
                });

        }
    }
}