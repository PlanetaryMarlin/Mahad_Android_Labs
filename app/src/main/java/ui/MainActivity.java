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
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());

        model = new ViewModelProvider(this).get(MainViewModel.class);

        setContentView(variableBinding.getRoot());
        setContentView(R.layout.activity_main);

        TextView mytext = variableBinding.textview;
        EditText myedit = variableBinding.myedittext;
        final Button btn = variableBinding.mybutton;


        variableBinding.textview.setText(model.editString);

        variableBinding.mybutton.setOnClickListener(click -> {
            model.editString.postValue(variableBinding.myedittext.getText().toString());
            variableBinding.textview.setText("Your Edit Text has: " + model.editString);
        });
    }
}

