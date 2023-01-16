package com.example.mahadandroidlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahadandroidlabs.databinding.ActivityChatRoomBinding;
import com.example.mahadandroidlabs.databinding.SentMessageBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChatRoom extends AppCompatActivity {

    ActivityChatRoomBinding binding;
    ChatRoomViewModel chatModel ;
    private ArrayList<ChatMessage> messages;
    private RecyclerView.Adapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();
            if(messages == null) {
                chatModel.messages.postValue( messages = new ArrayList<String>());
            }

        binding.sendButton.setOnClickListener(click -> {
            messages.add(binding.textInput.getText().toString());

            //Noitfy when changes happens
            myAdapter.notifyItemInserted(messages.size()-1);
            myAdapter.notifyItemRemoved(messages.size()-1);


            //clear previous text
            binding.textInput.setText("");
        });

        binding.retrieveButton.setOnClickListener(click -> {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
        });





        class MyRowHolder extends RecyclerView.ViewHolder {
            TextView messageText;
            TextView timeText;
            public MyRowHolder(@NonNull View itemView) {
                super(itemView);
                messageText = itemView.findViewById(R.id.messageText);
                timeText = itemView.findViewById(R.id.timeText);
            }
        }

        binding.recycleView.setAdapter(myAdapter=new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                return new MyRowHolder(binding.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                holder.messageText.setText("");
                holder.timeText.setText("");
                String obj = messages.get(position);
                holder.messageText.setText(obj);

            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            public int getItemViewType(int position){
                return 0;
            }
        });

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));


    }
}