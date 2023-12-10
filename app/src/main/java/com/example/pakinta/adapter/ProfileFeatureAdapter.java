package com.example.pakinta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pakinta.R;
import com.example.pakinta.model.ProfileFeatureModel;

import java.util.List;

public class ProfileFeatureAdapter extends RecyclerView.Adapter<ProfileFeatureAdapter.viewHolder> {
    private List<ProfileFeatureModel> profileFeatureModels;
    private Context context;

    public ProfileFeatureAdapter(List<ProfileFeatureModel> profileFeatureModels){
        this.profileFeatureModels = profileFeatureModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileFeatureAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_feature,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileFeatureAdapter.viewHolder holder, int position) {
        ProfileFeatureModel profileFeatureModel = profileFeatureModels.get(position);
        holder.iv_feature_image.setImageResource(profileFeatureModel.getFeature_image());
        holder.tv_feature_name.setText(profileFeatureModel.getTv_feature_name());
    }

    @Override
    public int getItemCount() {
        return profileFeatureModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_feature_image;
        private TextView tv_feature_name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            iv_feature_image = itemView.findViewById(R.id.iv_feature_image);
            tv_feature_name = itemView.findViewById(R.id.tv_feature_name);
        }

    }
}
