package com.prof.bcm.authentication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.prof.bcm.R;
import com.prof.bcm.constituency.ConstituencyActivity;
import com.prof.bcm.helper.DbProperties;
import com.prof.bcm.helper.InternetConnection;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private MaterialButton btn_login;
    private MaterialTextView tv_signup_link;
    private TextInputEditText et_email, et_password;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_signup_link = findViewById(R.id.tv_signup_link);
        btn_login = findViewById(R.id.btn_login);
        et_email = findViewById(R.id.et_login_email);
        et_password = findViewById(R.id.et_login_password);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);


        if (isLoggedIn()) {
            String email = auth.getCurrentUser().getEmail();
            setUserDetails(email);
        }

        tv_signup_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (InternetConnection.getInternetConnection().isConnected(getApplicationContext())) {
                    String email = et_email.getText().toString().trim();
                    String pass = et_password.getText().toString().trim();

                    login(email, pass);
                }
            }
        });
    }

    private boolean isLoggedIn() {
        return auth.getCurrentUser() != null;
    }

    private void login(String email, String pass) {

        //login checks
        if (email.isEmpty()) {
            Log.d(TAG, "email: " + email);
            et_email.setError("Email cannot be empty");
            Toast.makeText(LoginActivity.this, "Email field is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.isEmpty()) {
            et_password.setError("Password cannot be empty");
            Toast.makeText(LoginActivity.this, "Password field is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        loading();

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    setUserDetails(email);
                    return;
                }
                Log.e(TAG, "why failed: " + task.getException());
                Toast.makeText(LoginActivity.this, "An error occurred while trying to login\n" +
                        "Please make sure your mobile data is on and try again", Toast.LENGTH_LONG).show();
                dismissLoading();
            }
        });


    }

    private void setUserDetails(String email) {
        db.collection(DbProperties.USERS_Collection)
                .whereEqualTo(DbProperties.EMAIL_FIELD, email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<UserDetailsModel> userDetailsModels = task.getResult().toObjects(UserDetailsModel.class);
                            ApplicationUser.setUserDetails(new UserDetailsModel(
                                    userDetailsModels.get(0).getEmail(),
                                    userDetailsModels.get(0).getfName(),
                                    userDetailsModels.get(0).getlName(),
                                    userDetailsModels.get(0).getIsAdmin()
                            ));
                            startActivity(new Intent(LoginActivity.this, ConstituencyActivity.class));
                            dismissLoading();
                            finish();
                            return;
                        }

                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "onComplete: " + task.getException());
                    }
                });
    }


    private void loading() {
        if (!progressDialog.isShowing()) {
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Make sure your mobile data is on.\nTrying to login please wait ....");
            progressDialog.show();
        }
    }

    private void dismissLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}