package com.example.mahadandroidlabs;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface ChatMessageDAO {
    @Insert
    void insertMessage(ChatMessage m );

    @Query("Select * from ChatMessage")
    List<ChatMessage> getAllMessage();

    @Delete
    void deleteMessage(ChatMessage m);
}
