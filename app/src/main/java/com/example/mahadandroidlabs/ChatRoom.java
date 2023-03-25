package com.example.mahadandroidlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahadandroidlabs.databinding.ActivityChatRoomBinding;
import com.example.mahadandroidlabs.databinding.ReceiveMessageBinding;
import com.example.mahadandroidlabs.databinding.SentMessageBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

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
        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "database-name").build();
        ChatMessageDAO mDAO = db.cmDAO();

        chatModel.selectedMessage.observe(this, (newMessageValue) -> {
            MessageDetailsFragment chatFragment = new MessageDetailsFragment(newMessageValue);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentLocation, chatFragment)
                    .addToBackStack("")
                    .commit();

        });

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

                itemView.setOnClickListener(clk -> {
                    int position = getAbsoluteAdapterPosition();
                    ChatMessage selected = messages.get(position);

                    chatModel.selectedMessage.postValue(selected);

                   /*
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoom.this);
                    builder.setMessage("Do you want to delete the message: " + messageText.getText());
                        builder.setTitle("Question:");
                        builder.setNegativeButton("No", (dialog, cl) -> {});
                        builder.setPositiveButton("Yes", (dialog, cl) -> {
                            ChatMessage m = messages.get(position);
                            messages.remove(position);
                            myAdapter.notifyItemRemoved(position);

                            Snackbar.make(messageText, "You Deleted Message # "+position, Snackbar.LENGTH_LONG)
                                    .setAction("Undo", click->{
                                        messages.add(position, m);
                                        myAdapter.notifyItemRemoved(position);
                                    })
                                    .show();


                        }).create().show();*/
                });

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