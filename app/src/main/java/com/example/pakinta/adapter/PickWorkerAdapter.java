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
import com.example.pakinta.model.PickWorkerModel;

import java.util.List;

public class PickWorkerAdapter extends RecyclerView.Adapter<PickWorkerAdapter.viewHolder> {
    private List<PickWorkerModel> pickWorkerModels;
    private Context context;

    private AmountChangeListener amountChangeListener;

    public interface AmountChangeListener {
        void onAmountChanged(int position, int newAmount);
    }

    public void setAmountChangeListener(AmountChangeListener listener) {
        this.amountChangeListener = listener;
    }

    public PickWorkerAdapter(List<PickWorkerModel> pickWorkerModels) {
        this.pickWorkerModels = pickWorkerModels;
        this.context = context;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pick_worker, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PickWorkerAdapter.viewHolder holder, int position) {
        PickWorkerModel pickWorkerModel = pickWorkerModels.get(position);

        holder.tv_specialist.setText(pickWorkerModel.getSpecialist());
        holder.tv_price.setText(pickWorkerModel.getPrice());
        holder.tv_amount_worker.setText(String.valueOf(pickWorkerModel.getAmountWorker()));
        holder.tv_description.setText(pickWorkerModel.getDescription());

        holder.iv_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                int amount = Integer.parseInt(holder.tv_amount_worker.getText().toString());
                if (amount != 0) {
                    amount--;
                    holder.tv_amount_worker.setText(String.valueOf(amount));
                    if (amountChangeListener != null) {
                        amountChangeListener.onAmountChanged(position, amount);
                    }
                }
            }
        });

        holder.iv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                int amount = Integer.parseInt(holder.tv_amount_worker.getText().toString());
                amount++;
                holder.tv_amount_worker.setText(String.valueOf(amount));
                if (amountChangeListener != null) {
                    amountChangeListener.onAmountChanged(position, amount);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pickWorkerModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private TextView tv_specialist, tv_price, tv_amount_worker, tv_description;
        private ImageView iv_min, iv_plus;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tv_specialist = itemView.findViewById(R.id.tv_specialist);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_amount_worker = itemView.findViewById(R.id.tv_amount_worker);
            tv_description = itemView.findViewById(R.id.tv_description);

            iv_min = itemView.findViewById(R.id.iv_min);
            iv_plus = itemView.findViewById(R.id.iv_plus);
        }
    }
}
