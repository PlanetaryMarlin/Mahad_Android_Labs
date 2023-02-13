package com.example.mahadandroidlabs;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconds);
        prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        Button changePicture = findViewById(R.id.changePicture);
        Button callButton = findViewById(R.id.callButton);
        EditText phoneNumberText = findViewById(R.id.phoneNumberText);
        ImageView picture = findViewById(R.id.picture);
        TextView welcomeMessage = findViewById(R.id.welcomeMessage);

        Intent fromPrevious = getIntent();

        //Retrieve the data from the first page, and transform the text.
        String emailAddress = fromPrevious.getStringExtra("Email Address");

        // Display User Email when they travel to next page
        String message = "Welcome Back " + emailAddress;
        welcomeMessage.setText(message);

        // Phone
        callButton.setOnClickListener( clk-> {

            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + phoneNumberText.getText()));
            startActivity(call);

        });

        File file = new File( getFilesDir(), "Picture.png");

        if(file.exists()) {
            Bitmap theImage = BitmapFactory.decodeFile(file.getAbsolutePath());
            picture.setImageBitmap( theImage );

        }


        // Picture
        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Bitmap thumbnail = data.getParcelableExtra("data");
                            picture.setImageBitmap(thumbnail);

                            FileOutputStream fOut = null;

                            try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
                                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                                fOut.flush();
                                fOut.close();
                            }

                            catch (FileNotFoundException e)
                            { e.printStackTrace();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }




                    }
                });
        changePicture.setOnClickListener( clk-> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraResult.launch(cameraIntent);
        });



        }

    @Override
    protected void onPause() {
        EditText phoneNumberText = findViewById(R.id.phoneNumberText);
        super.onPause();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Phone Number",  phoneNumberText.getText().toString());
        editor.apply();
    }
    }
