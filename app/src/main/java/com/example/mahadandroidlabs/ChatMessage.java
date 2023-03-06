package com.example.mahadandroidlabs;

public class ChatMessage {
    String message;
    String timeSent;
    boolean isSend;


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