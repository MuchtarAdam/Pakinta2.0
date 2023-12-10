package com.example.pakinta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pakinta.R;
import com.example.pakinta.model.RiwayatModel;

import java.util.List;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.viewHolder> {
    private List<RiwayatModel> riwayatModels;
    private Context context;

    public RiwayatAdapter(List<RiwayatModel> riwayatModels) {
        this.riwayatModels = riwayatModels;
        this.context = context;
    }

    @NonNull
    @Override
    public RiwayatAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatAdapter.viewHolder holder, int position) {
        RiwayatModel riwayatModel = riwayatModels.get(position);

        holder.iv_logo_riwayat.setImageResource(riwayatModel.getImage());
        holder.tv_project_name.setText(riwayatModel.getProject_name());
        holder.tv_status.setText(riwayatModel.getStatus());
        holder.tv_date_time.setText(riwayatModel.getDate_time());
        holder.tv_price.setText(riwayatModel.getPrice());
    }

    @Override
    public int getItemCount() {
        return riwayatModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_logo_riwayat;
        private TextView tv_project_name, tv_status, tv_date_time, tv_price;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            iv_logo_riwayat = itemView.findViewById(R.id.iv_logo_riwayat);
            tv_project_name =  itemView.findViewById(R.id.tv_project_name);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_date_time = itemView.findViewById(R.id.tv_date_time);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
