package com.example.mahadandroidlabs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {
    @PrimaryKey (autoGenerate=true)
    @ColumnInfo(name="id")
    public int id;
    @ColumnInfo(name="message")
    protected String message;
    @ColumnInfo(name="TimeSent")
    protected String timeSent;
    @ColumnInfo(name="SendOrReceive")
    protected boolean isSend;




    public ChatMessage (String message, String timeSent, boolean isSend) {
        this.message = message;
        this.timeSent = timeSent;
        this.isSend = isSend;


    }


    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public boolean isSend() {
        return isSend;
    }


}