package com.example.pakinta.adapter;

import android.content.Context;
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
import com.example.pakinta.model.MaterialModel;

import java.util.ArrayList;
import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.viewHolder> {
    private ArrayList<MaterialModel> materialModels;
    private Context context;

    public MaterialAdapter(ArrayList<MaterialModel> materialModels, Context context) {
        this.materialModels = materialModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MaterialAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialAdapter.viewHolder holder, int position) {
            MaterialModel materialModel = materialModels.get(position);

        Glide.with(context)
                .load(materialModel.getImage()) // Assuming the MaterialModel has a method getImageUrl() to retrieve the image URL
                .apply(new RequestOptions().override(800, 800)
                        .centerCrop()) // Apply image size and center crop transformation
                .into(holder.iv_image_material);

            holder.tv_name_material.setText(materialModel.getName());
        }

    @Override
    public int getItemCount() {
        return materialModels.size();
    }

    public void filterList(ArrayList<MaterialModel> filteredList) {
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_image_material;
        private TextView tv_name_material;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            iv_image_material = itemView.findViewById(R.id.iv_image_material);
            tv_name_material = itemView.findViewById(R.id.tv_name_material);
        }
    }
}
