package com.example.pakinta.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.pakinta.R;
import com.example.pakinta.activity.MaterialActivity;
import com.example.pakinta.activity.PembangunanActivity;
import com.example.pakinta.activity.PembangunanNullActivity;
import com.example.pakinta.model.HomeServiceModel;

import java.util.List;

public class HomeServiceAdapter extends RecyclerView.Adapter<HomeServiceAdapter.viewHolder> {
    private List<HomeServiceModel> homeServiceModels;
    private Context context;

    public HomeServiceAdapter(List<HomeServiceModel> homeServiceModels) {
        this.homeServiceModels = homeServiceModels;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeServiceAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_service,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeServiceAdapter.viewHolder holder, int position) {
        HomeServiceModel homeServiceModel = homeServiceModels.get(position);

        Glide.with(holder.itemView.getContext())
                .load(homeServiceModel.getHome_service_image())
                .apply(new RequestOptions().override(150,150))
                .into(holder.iv_home_service_image);

        holder.iv_home_service_arrow.setImageResource(homeServiceModel.getHome_service_arrow());
        holder.tv_home_service_title.setText(homeServiceModel.getHome_service_title());
        holder.tv_home_service_description.setText(homeServiceModel.getHome_service_description());

        if (position == 2) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(), MaterialActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        } else if (position == 1){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.itemView.getContext(), PembangunanNullActivity.class);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return homeServiceModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_home_service_image, iv_home_service_arrow;
        private TextView tv_home_service_title, tv_home_service_description;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            iv_home_service_image = itemView.findViewById(R.id.iv_home_service_image);
            iv_home_service_arrow = itemView.findViewById(R.id.iv_home_service_arrow);
            tv_home_service_title = itemView.findViewById(R.id.tv_home_service_title);
            tv_home_service_description = itemView.findViewById(R.id.tv_home_service_description);
        }
    }
}
