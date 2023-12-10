package com.example.pakinta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pakinta.R;
import com.example.pakinta.model.ChatModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.viewHolder> {
    private List<ChatModel> chats;
    private Context context;

    public ChatAdapter(List<ChatModel> chats){
        this.chats = chats;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.viewHolder holder, int position) {
        ChatModel chat = chats.get(position);
        holder.tv_name.setText(chat.getName());
        holder.tv_chat.setText(chat.getChat());
        holder.tv_time.setText(chat.getTime());

        Glide.with(holder.itemView.getContext())
                .load(chat.getProfile_image())
                .apply(new RequestOptions().override(45,45))
                .into(holder.civ_profile_image);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class viewHolder extends  RecyclerView.ViewHolder{

        LinearLayout ll_parentChat;
        private CircleImageView civ_profile_image;
        private TextView tv_name, tv_chat, tv_time;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            civ_profile_image = itemView.findViewById(R.id.civ_profile_image);
            tv_name =  itemView.findViewById(R.id.tv_name);
            tv_chat = itemView.findViewById(R.id.tv_chat);
            tv_time = itemView.findViewById(R.id.tv_time);
            ll_parentChat = itemView.findViewById(R.id.ll_parentChat);
        }
    }
}
