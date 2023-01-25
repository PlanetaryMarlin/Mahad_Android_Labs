package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.mahadandroidlabs.R;
import com.example.mahadandroidlabs.data.MainViewModel;
import com.example.mahadandroidlabs.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(variableBinding.getRoot());

        model.isSelected.observe(this, selected -> {
            variableBinding.myCheck.setChecked(selected);
            variableBinding.mySwitch.setChecked(selected);
            variableBinding.myRadio.setChecked(selected);
        });




        // Saves the text when button is clicked. In the MainViewModel Class, to prevent deletion during turning the screen.
        variableBinding.mybutton.setOnClickListener(click -> {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
        });

        //Set the text on the text box.
        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has " + s );

        });


    }

}

