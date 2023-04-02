package com.example.mahadandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mahadandroidlabs.databinding.ActivityMainBinding;


/**
 * This page allows user to type in a password, and check how complex the password is through various functions.
 * @version 1.0
 * @author Mahad Mohamed
 */
public class MainActivity extends AppCompatActivity {

    protected String cityName;
    RequestQueue queue = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate( getLayoutInflater() );

        //This part goes at the top of the onCreate function:
        queue = Volley.newRequestQueue(this);


        binding.getForecast.setOnClickListener(click -> {
            cityName = binding.cityTextField.getText().toString();
            String stringURL = "";

            //this goes in the button click handler:
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                    (response) -> {   },
                    (error) -> {   });
            queue.add(request);

        });
    }

    /**
     * @param pw The String object that we are checking
     * @return return true if password is complex enough.
     */
    boolean checkPasswordComplexity(String pw) {
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;
        boolean complexEnough = true;


        for (int i = 0; i <pw.length(); i++) {
            char c = pw.charAt(i);

            if (Character.isUpperCase(c))
                foundUpperCase = true;
            else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            }
            else if (Character.isDigit(c)) {
                foundNumber = true;
            }

            /**
             * Checks if the character is not a number, letter, or empty spaces. If's not any of this, then it's considered special.
             */
            else if (!Character.isDigit(c)&& !Character.isLetter(c)&& !Character.isWhitespace(c)) {
                foundSpecial = true;
            }


        }

        if(!foundUpperCase) {
            Toast.makeText(this,"Missing Uppercase letter",Toast.LENGTH_SHORT).show();  ;// Say that they are missing an upper case letter;
            return false ;

        }
        else if( ! foundLowerCase) {
            Toast.makeText(this,"Missing Lowercase letter",Toast.LENGTH_SHORT).show();  // Say that they are missing a lower case letter;
            return false;
        }

        else if( ! foundNumber) {
            Toast.makeText(this,"Missing Number",Toast.LENGTH_SHORT).show();  // Say that they are missing a lower case letter;
            return false;
        }

        else if( ! foundSpecial) {
            Toast.makeText(this,"Missing Special",Toast.LENGTH_SHORT).show();  // Say that they are missing a lower case letter;
            return false;
        }


        return complexEnough;


    }


    }
