package com.prof.bcm.contribution;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;
import com.prof.bcm.R;

import java.util.List;

public class ContributorAdapter extends RecyclerView.Adapter<ContributorAdapter.MyViewHolder> {

    private List<Contributor> contributorList;

    public ContributorAdapter(List<Contributor> contributorList) {
        this.contributorList = contributorList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contribution_inner_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (!contributorList.isEmpty()){
            holder.tv_contributor_name.setText(contributorList.get(position).getName());
            holder.tv_contributor_amount.setText(String.valueOf(contributorList.get(position).getAmountContributed()));
        }
    }

    @Override
    public int getItemCount() {
        return contributorList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView tv_contributor_amount;
        private MaterialTextView tv_contributor_name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_contributor_name = itemView.findViewById(R.id.tv_contributor_name);
            tv_contributor_amount = itemView.findViewById(R.id.tv_contributor_amount);
        }
    }
}
