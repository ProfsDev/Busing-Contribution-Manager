package com.prof.bcm.contribution;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.prof.bcm.R;
import com.prof.bcm.edit_contribution.EditContributionActivity;
import com.prof.bcm.helper.DbProperties;
import com.prof.bcm.helper.MonthsOfYear;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContributionActivity extends AppCompatActivity implements OnContributionRecyclerViewListener {

    private static final String TAG = "ContributionActivity";
    public static final String KEY_NAME = "name";
    public static final String KEY_AMOUNT_CONTRIBUTED = "amountContributed";
    private MaterialTextView tv_name_of_bacenta;
    private RecyclerView contribution_recycler_view;
    private static String BACENTA_NAME;
    private FirebaseFirestore db;
    private ProgressDialog progressDialog;
    private ContributionAdapter contributionAdapter;
    private List<ContributionModel> contributionModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution);

        tv_name_of_bacenta = findViewById(R.id.tv_name_of_bacenta);
        BACENTA_NAME = this.getIntent().getStringExtra(KEY_NAME);
        contribution_recycler_view = findViewById(R.id.contribution_recycler_view);
        progressDialog = new ProgressDialog(this);
        db = FirebaseFirestore.getInstance();
        contributionModelList = new ArrayList<>();
        contributionAdapter = new ContributionAdapter(this, contributionModelList, this);

        tv_name_of_bacenta.setText(BACENTA_NAME);
        populateContributionRecyclerView();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        contribution_recycler_view.setLayoutManager(linearLayoutManager);
        contribution_recycler_view.setAdapter(contributionAdapter);
    }

    private void populateContributionRecyclerView() {
        contributionModelList.clear();
        showProgress(true);
        for (MonthsOfYear month : MonthsOfYear.values()) {
            db.collection(DbProperties.MONTHLY_CONTRIBUTION_COLLECTION)
                    .document(month.name())
                    .collection(BACENTA_NAME)
                    .orderBy(KEY_NAME)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            if (e != null) {
                                Toast.makeText(ContributionActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e(TAG, "onEvent: " + e);
                                showProgress(false);
                                return;
                            }

                            List<Contributor> contributorList = new ArrayList<>();
                            List<DocumentChange> documentChangeList = queryDocumentSnapshots.getDocumentChanges();

                            List<DocumentChange> modifiedDocumentList = documentChangeList
                                    .stream()
                                    .filter(documentChange -> documentChange.getType().equals(DocumentChange.Type.MODIFIED))
                                    .collect(Collectors.toList());

                            if (!modifiedDocumentList.isEmpty()) {

                                contributionModelList.forEach(contributionModel -> {
                                    if (contributionModel.getMonth().equals(month.name())) {
                                        modifiedDocumentList.forEach(modifiableContributor -> {
                                            int sizeOfContributorsForTheMonth = contributionModel.getContributorList().size();
                                            for (int i = 0; i < sizeOfContributorsForTheMonth; i++) {
                                                Contributor modifiableContributorOBJ = modifiableContributor.getDocument().toObject(Contributor.class);
                                                if (modifiableContributorOBJ.getName().equals(contributionModel.getContributorList().get(i).getName())){
                                                    contributionModel.getContributorList().get(i).setAmountContributed(modifiableContributorOBJ.getAmountContributed());
                                                    break;
                                                }
                                            }
                                        });
                                    }
                                });

                                contributionAdapter.notifyDataSetChanged();

                            } else {
                                documentChangeList.forEach(documentChange -> {
                                    Contributor contributor = documentChange.getDocument().toObject(Contributor.class);
                                    contributorList.add(contributor);
                                });

                                contributionModelList.add(new ContributionModel(month.name(), contributorList));
                                contributionAdapter.notifyDataSetChanged();
                            }


                            showProgress(false);
                        }
                    });
        }
    }


    private void showProgress(boolean show) {
        if (show) {
            progressDialog.setMessage("Please wait...");
            progressDialog.show();
            return;
        }
        progressDialog.dismiss();
    }

    @Override
    public void onEditClicked(int position) {
        Intent intent = new Intent(this, EditContributionActivity.class);
        intent.putExtra("bacenta", BACENTA_NAME);
        intent.putExtra("month", contributionModelList.get(position).getMonth());
        startActivity(intent);
    }
}