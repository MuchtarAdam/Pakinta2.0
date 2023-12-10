package com.example.pakinta.datasource;

import com.example.pakinta.R;
import com.example.pakinta.model.ChatModel;

import java.util.ArrayList;
import java.util.List;

public class ChatDataSource {
    List<ChatModel> chatModels;

    public List<ChatModel> getChatModels() {
        chatModels = new ArrayList<>();
        chatModels.add(new ChatModel(R.drawable.baseline_account_circle_24,"Fikra","Turu","23:15"));
        chatModels.add(new ChatModel(R.drawable.baseline_account_circle_24,"Fikri","Sanging UB FYP ku","17:00"));
        chatModels.add(new ChatModel(R.drawable.baseline_account_circle_24,"Argy","Mana kunci motorku?","16:15"));
        chatModels.add(new ChatModel(R.drawable.baseline_account_circle_24,"Mursyid","Login gy","12:40"));

        return chatModels;
    }
}
