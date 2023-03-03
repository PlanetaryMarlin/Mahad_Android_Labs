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

            //checkPasswordComplexity(password);

            if (checkPasswordComplexity(password) == true){
                tv.setText("Your password meets the requirements");
            }
            else if (checkPasswordComplexity(password) == false) {
                tv.setText("You Shall Not Pass!");
            }
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
