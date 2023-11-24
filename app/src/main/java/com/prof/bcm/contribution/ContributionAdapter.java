package com.prof.bcm.contribution;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.prof.bcm.R;
import com.prof.bcm.authentication.ApplicationUser;
import com.prof.bcm.constituency.BacentaAdapter;

import java.util.List;

public class ContributionAdapter extends RecyclerView.Adapter<ContributionAdapter.MyViewHolder> {

    private Context context;
    private List<ContributionModel> contributionModelList;
    private OnContributionRecyclerViewListener listener;

    public ContributionAdapter(Context context, List<ContributionModel> contributionModelList, OnContributionRecyclerViewListener listener) {
        this.contributionModelList = contributionModelList;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contribution_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (contributionModelList != null || !contributionModelList.isEmpty()) {
            holder.tv_month_heading.setText(contributionModelList.get(position).getMonth());
            holder.tv_total_amount_figure.setText(String.valueOf(contributionModelList.get(position).getTotal()));

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            ContributorAdapter contributorAdapter = new ContributorAdapter(contributionModelList.get(position).getContributorList());
            holder.contribution_inner_recycler_view.setLayoutManager(linearLayoutManager);
            holder.contribution_inner_recycler_view.setAdapter(contributorAdapter);

        }

        if (ApplicationUser.getUserDetails().getIsAdmin()) {
            holder.btn_edit.setVisibility(View.VISIBLE);
        } else {
            holder.btn_edit.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return contributionModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView tv_month_heading;
        private MaterialTextView tv_total_amount_figure;
        private MaterialButton btn_edit;
        private RecyclerView contribution_inner_recycler_view;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_month_heading = itemView.findViewById(R.id.tv_month_heading);
            tv_total_amount_figure = itemView.findViewById(R.id.tv_total_amount_figure);
            btn_edit = itemView.findViewById(R.id.btn_edit);
            contribution_inner_recycler_view = itemView.findViewById(R.id.contribution_inner_recycler_view);

            btn_edit.setOnClickListener((view) -> {
                if (listener != null){
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION){
                        listener.onEditClicked(position);
                    }
                }
            });
        }
    }
}
