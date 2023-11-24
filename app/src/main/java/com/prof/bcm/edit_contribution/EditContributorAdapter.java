package com.prof.bcm.edit_contribution;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.prof.bcm.R;
import com.prof.bcm.contribution.ContributionModel;
import com.prof.bcm.contribution.Contributor;

import java.util.List;

public class EditContributorAdapter extends RecyclerView.Adapter<EditContributorAdapter.MyViewHolder> {
    private static final String TAG = "EditContributorAdapter";
    private List<EditContributionModel> editContributionModelList;
    private OnUpdateContributionListener listener;

    public EditContributorAdapter(List<EditContributionModel> editContributionModelList, OnUpdateContributionListener listener) {
        this.editContributionModelList = editContributionModelList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.edit_contribution_list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (editContributionModelList != null || !editContributionModelList.isEmpty()){
            holder.tv_contributor_name.setText(editContributionModelList.get(position).getName());
            holder.tv_contributor_amount.setText(String.valueOf(editContributionModelList.get(position).getAmountContributed()));
        }
    }

    @Override
    public int getItemCount() {
        return editContributionModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView tv_contributor_name;
        private TextInputEditText tv_contributor_amount;
        private MaterialButton btn_update;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_contributor_name = itemView.findViewById(R.id.tv_contributor_name);
            tv_contributor_amount = itemView.findViewById(R.id.tv_contributor_amount);
            btn_update = itemView.findViewById(R.id.btn_update);

            btn_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){

                            Log.d(TAG, "onClick: " + editContributionModelList.get(position));
                            String id = editContributionModelList.get(position).getId();
                            String name = tv_contributor_name.getText().toString();
                            Double amountContributed = Double.valueOf(tv_contributor_amount.getText().toString());
                            listener.onUpdate(editContributionModelList.get(position), amountContributed);
                        }
                    }
                }
            });
        }
    }
}
