package com.example.pakinta.model;

public class ChatModel {
    private int profile_image;
    private String name, chat, time;

    public ChatModel(int profile_image, String name, String chat, String time) {
        this.profile_image = profile_image;
        this.name = name;
        this.chat = chat;
        this.time = time;
    }

    public int getProfile_image() {
        return profile_image;
    }
    public String getName() {
        return name;
    }
    public String getChat() {
        return chat;
    }

    public String getTime() {
        return time;
    }
}
