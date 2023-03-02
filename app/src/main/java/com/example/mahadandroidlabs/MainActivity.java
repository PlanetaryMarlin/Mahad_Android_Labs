package com.example.mahadandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**   This is a Javadoc comment */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.editText);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(clk -> {
            String password = et.getText().toString();

            checkPasswordComplexity(password);

        });
    }

        /**
         * @param pw The String object that we are checking
         * @return return true if password is complex.
         */
        boolean checkPasswordComplexity(String pw) {
            boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
            foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

            for
                if(!foundUpperCase) {
                    Toast.makeText(getApplicationContext(),"Missing Uppercase letter",Toast.LENGTH_SHORT).show();  ;// Say that they are missing an upper case letter;
                    return false ;

                }
                else if( ! foundLowerCase) {
                    Toast.makeText(getApplicationContext(),"Missing Lowercase letter",Toast.LENGTH_SHORT).show();  // Say that they are missing a lower case letter;
                    return false;
                }

                else if( ! foundNumber) {
                    Toast.makeText(getApplicationContext(),"Missing Number",Toast.LENGTH_SHORT).show();  // Say that they are missing a lower case letter;
                    return false;
                }



                else
                    return true;

                boolean isSpecialCharacter(char c){
                    switch (c) {
                        case '#':
                        case '?':
                        case '*':
                            return true;
                        default:
                            Toast.makeText(getApplicationContext(), "Missing Special Symbol", Toast.LENGTH_SHORT).show();  // Say that they are missing a lower case letter;
                            return false;

                    }
                }
        }

    }
