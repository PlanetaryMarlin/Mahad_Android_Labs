package com.example.mahadandroidlabs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.mahadandroidlabs.databinding.DetailsLayoutBinding;

public class MessageDetailsFragment extends Fragment {

    ChatMessage selected;

    public MessageDetailsFragment (ChatMessage m) {
        selected =m ;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater);
        binding.messageText2.setText(selected.message);
        binding.timeText2.setText(selected.timeSent);
        binding.databaseText.setText("ID ="+selected.id);


        return binding.getRoot();
    }
}
