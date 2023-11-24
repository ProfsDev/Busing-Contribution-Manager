package com.prof.bcm.constituency;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.prof.bcm.R;

import java.util.List;

public class ConstituencyAdapter extends RecyclerView.Adapter<ConstituencyAdapter.MyViewHolder> {

    private List<ConstituencyModel> constituencyModelList;
    private OnRecyclerViewListener listener;

    public ConstituencyAdapter(List<ConstituencyModel> constituencyModelList, OnRecyclerViewListener listener) {
        this.constituencyModelList = constituencyModelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.constituency_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (constituencyModelList != null || !constituencyModelList.isEmpty()){
            holder.tv_constituency_name.setText(constituencyModelList.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return constituencyModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private MaterialTextView tv_constituency_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_constituency_name = itemView.findViewById(R.id.tv_constituency_name);
            tv_constituency_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            String name = tv_constituency_name.getText().toString();
                            listener.onConstituencyRecyclerViewClick(position, name);
                        }
                    }
                }
            });
        }
    }
}
