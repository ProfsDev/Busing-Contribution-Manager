package com.prof.bcm.constituency;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.prof.bcm.R;

import java.util.List;

public class BacentaAdapter extends RecyclerView.Adapter<BacentaAdapter.MyViewHolder> {

    private List<BacentaModel> bacentaModelList;
    private OnRecyclerViewListener listener;

    public BacentaAdapter(List<BacentaModel> bacentaModelList, OnRecyclerViewListener listener) {
        this.bacentaModelList = bacentaModelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bacenta_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (bacentaModelList != null || !bacentaModelList.isEmpty()) {
            holder.tv_bacenta_name.setText(bacentaModelList.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return bacentaModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView tv_bacenta_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_bacenta_name = itemView.findViewById(R.id.tv_bacenta_name);

            tv_bacenta_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            String name = tv_bacenta_name.getText().toString();
                            Toast.makeText(itemView.getContext(), name, Toast.LENGTH_SHORT).show();
                            listener.onBacentaRecyclerViewClicked(position, name);
                        }
                    }
                }
            });
        }
    }
}
