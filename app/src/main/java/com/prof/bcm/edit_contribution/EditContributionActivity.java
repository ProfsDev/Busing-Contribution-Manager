package com.prof.bcm.edit_contribution;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.prof.bcm.R;
import com.prof.bcm.helper.DbProperties;

import java.util.ArrayList;
import java.util.List;

public class EditContributionActivity extends AppCompatActivity implements OnUpdateContributionListener {

    private static final String TAG = "EditContributionActivit";
    public static final String KEY_BACENTA = "bacenta";
    public static final String KEY_MONTH = "month";
    private RecyclerView edit_contributor_recycler_view;
    private EditContributorAdapter editContributorAdapter;
    private List<EditContributionModel> editContributionModelList;
    private MaterialTextView tv_bacenta_name;
    private MaterialTextView tv_month;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contribution);

        edit_contributor_recycler_view = findViewById(R.id.edit_contributor_recycler_view);
        tv_bacenta_name = findViewById(R.id.tv_bacenta_name);
        tv_month = findViewById(R.id.tv_month);
        editContributionModelList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        tv_bacenta_name.setText(this.getIntent().getStringExtra(KEY_BACENTA));
        tv_month.setText(this.getIntent().getStringExtra(KEY_MONTH));

        populate();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        editContributorAdapter = new EditContributorAdapter(editContributionModelList, this);
        edit_contributor_recycler_view.setLayoutManager(linearLayoutManager);
        edit_contributor_recycler_view.setAdapter(editContributorAdapter);
    }

    private void populate() {
        db.collection(DbProperties.MONTHLY_CONTRIBUTION_COLLECTION)
                .document(tv_month.getText().toString())
                .collection(tv_bacenta_name.getText().toString())
                .orderBy("name")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(EditContributionActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT);
                            Log.e(TAG, "onComplete: " + task.getException());
                            return;
                        }

                        List<DocumentChange> documentChangeList = task.getResult().getDocumentChanges();
                        boolean isEmpty = documentChangeList.isEmpty();
                        if (!isEmpty) {
                            documentChangeList.forEach((documentChange) -> {
                                EditContributionModel editContributionModel = documentChange.getDocument().toObject(EditContributionModel.class);
                                editContributionModel.setId(documentChange.getDocument().getId());
                                editContributionModelList.add(editContributionModel);
                            });

                            editContributorAdapter.notifyDataSetChanged();
                        }
                    }
                });
}

    @Override
    public void onUpdate(EditContributionModel editContributionModel, double newAmount) {
        editContributionModel.setAmountContributed(newAmount);
        db.collection(DbProperties.MONTHLY_CONTRIBUTION_COLLECTION)
                .document(tv_month.getText().toString())
                .collection(tv_bacenta_name.getText().toString())
                .document(editContributionModel.getId())
                .set(editContributionModel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(EditContributionActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onComplete: " + task.getException());
                            return;
                        }

                        Toast.makeText(EditContributionActivity.this, "Amount updated successfully", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}