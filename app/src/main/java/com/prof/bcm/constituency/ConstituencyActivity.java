package com.prof.bcm.constituency;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.prof.bcm.R;
import com.prof.bcm.authentication.ApplicationUser;
import com.prof.bcm.authentication.LoginActivity;
import com.prof.bcm.contribution.ContributionActivity;
import com.prof.bcm.helper.DbProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class ConstituencyActivity extends AppCompatActivity implements OnRecyclerViewListener {
    private static final String TAG = "ConstituencyActivity";
    private TextView cat_date;
    private RecyclerView constituency_recycler_view, bacenta_recycler_view;
    private List<ConstituencyModel> constituencyModelList;
    private List<BacentaModel> bacentaModelList;
    private ConstituencyAdapter constituencyAdapter;
    private BacentaAdapter bacentaAdapter;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private String isAdmin;
    private LocalDate date;
    private static Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constituency);

        date = LocalDate.now();

        cat_date = findViewById(R.id.cat_date);
        constituency_recycler_view = findViewById(R.id.constituency_recycler_view);
        bacenta_recycler_view = findViewById(R.id.bacenta_recycler_view);
        constituencyModelList = new ArrayList<>();
        bacentaModelList = new ArrayList<>();


        //getting the instance of connected database
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        context = ConstituencyActivity.this;



        cat_date.setText(date.getMonth().toString() + ", " + date.getYear());

        loading(context);

        constituencyAdapter = new ConstituencyAdapter(constituencyModelList, this);
        bacentaAdapter = new BacentaAdapter(bacentaModelList, this);


        populateConstituencies();
        setConstituencyAdapter();
    }

    private void setConstituencyAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        constituency_recycler_view.setLayoutManager(linearLayoutManager);
        constituency_recycler_view.setAdapter(constituencyAdapter);
    }


    private void populateConstituencies() {
        db.collection(DbProperties.CONSTITUENCY_COLLECTION)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {//detect if there is any change in the database
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            dismissLoading();
                            Log.e(TAG, error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getDocument() != null) {
                                if (dc.getType() == DocumentChange.Type.ADDED) {
                                    constituencyModelList.add(dc.getDocument().toObject(ConstituencyModel.class));
                                }
                            }

                            constituencyAdapter.notifyDataSetChanged();
                        }

                        populateBacenta();
                        setBacentaAdapter();
                    }
                });


    }

    private void populateBacenta() {
        if (!constituencyModelList.isEmpty()) {
        String name = constituencyModelList.get(0).getName();

            db.collection(name)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {//adding change listener to detect any change in database
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (error != null) {
                                dismissLoading();
                                Log.e(TAG, error.getMessage());
                                return;
                            }


                            for (DocumentChange dc : value.getDocumentChanges()) {
                                if (dc != null) {
                                    if (dc.getType().equals(DocumentChange.Type.ADDED)) {
                                        bacentaModelList.add(dc.getDocument().toObject(BacentaModel.class));
                                    }
                                }

                                bacentaAdapter.notifyDataSetChanged();
                            }

                        }
                    });
            dismissLoading();
        }
    }


    private void setBacentaAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        bacenta_recycler_view.setLayoutManager(layoutManager);
        bacenta_recycler_view.setAdapter(bacentaAdapter);
    }

    public void loading(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data ....");
        progressDialog.show();
    }

    public void dismissLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void onConstituencyRecyclerViewClick(int position, String name) {
        loading(context);
        db.collection(name).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null){
                    Log.e(TAG, error.getMessage());
                    Toast.makeText(context, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show();
                    dismissLoading();
                    return;
                }

                bacentaModelList.clear();
                for (DocumentChange dc : value.getDocumentChanges()){
                    if (dc != null){

                        if (dc.getType().equals(DocumentChange.Type.ADDED) || dc.getType().equals(DocumentChange.Type.MODIFIED)){
                            bacentaModelList.add(dc.getDocument().toObject(BacentaModel.class));
                        }
                    }
                }
                bacentaAdapter.notifyDataSetChanged();
                dismissLoading();
            }
        });
    }

    @Override
    public void onBacentaRecyclerViewClicked(int position, String name) {
        Intent it = new Intent(ConstituencyActivity.this, ContributionActivity.class);
        it.putExtra("name", name);
        startActivity(it);
    }


    public void fabOnClick(View view) {
        Log.d(TAG, "fabOnClick: " + ApplicationUser.getUserDetails());
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.fab_popup_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                ApplicationUser.removeUser();
                startActivity(new Intent(ConstituencyActivity.this, LoginActivity.class));
                finish();
                return true;
            }
        });
        popupMenu.show();
    }
}