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
import com.example.mahadandroidlabs.databinding.ReceiveMessageBinding;
import com.example.mahadandroidlabs.databinding.SentMessageBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//Notes:
//Recycler View is list that can show a various number of elements, and the user can scroll through the elements:
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
                chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());
            }




        binding.sendButton.setOnClickListener(click -> {
            String messageText = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage message = new ChatMessage(messageText, currentDateandTime, true);
            messages.add(message);

            //Noitfy when changes happens
            myAdapter.notifyItemInserted(messages.size()-1);
            myAdapter.notifyItemRemoved(messages.size()-1);
            myAdapter.notifyDataSetChanged();


            //clear previous text
            binding.textInput.setText("");
        });

        binding.retrieveButton.setOnClickListener(click -> {
            String messageText = binding.textInput.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateandTime = sdf.format(new Date());
            ChatMessage message = new ChatMessage(messageText, currentDateandTime, false);
            messages.add(message);
            myAdapter.notifyItemInserted(messages.size()-1);
            myAdapter.notifyDataSetChanged();
            binding.textInput.setText("");
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
            //It represents a single row in the list
            //MyRowHolder is an object for representing everything that goes on a row in the list.
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == 0) {
                    SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                } else {
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater());
                    return new MyRowHolder(binding.getRoot());
                }

            }

            //What are the text view set to.
            //This initializes a ViewHolder to go at the row specified by the position parameter.
            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                holder.messageText.setText("");
                holder.timeText.setText("");
                ChatMessage chatMessage = messages.get(position);
                holder.messageText.setText(chatMessage.getMessage());
                holder.timeText.setText(chatMessage.getTimeSent());

            }

            @Override
            public int getItemCount() {
                return messages.size();
            }

            public int getItemViewType(int position){
                ChatMessage message = messages.get(position);
                if (message.isSend()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));


    }
}