package com.example.pakinta.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.pakinta.R;
import com.example.pakinta.adapter.ChatAdapter;
import com.example.pakinta.datasource.ChatDataSource;
import com.example.pakinta.model.ChatModel;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private RecyclerView recyclerView;
    private RelativeLayout topBar;
    private LinearLayout ll_container_chat;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerView = view.findViewById(R.id.rv_chat);

        topBar = view.findViewById(R.id.topBar);
        ll_container_chat = view.findViewById(R.id.ll_container_chat);
        progressBar = view.findViewById(R.id.progressBar);

        topBar.setVisibility(View.INVISIBLE);
        ll_container_chat.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                topBar.setVisibility(View.VISIBLE);
                ll_container_chat.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        },750);

        // Create an instance of ChatDataSource
        ChatDataSource dataSource = new ChatDataSource();

        // Fetch the chat models from the data source
        List<ChatModel> chatModels = dataSource.getChatModels();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ChatAdapter chatAdapter = new ChatAdapter(chatModels);
        recyclerView.setAdapter(chatAdapter);

        return view;
    }
}
