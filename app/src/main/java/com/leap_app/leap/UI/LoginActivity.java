package com.leap_app.leap.UI;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.authentication.Constants;
import com.firebase.client.core.SyncPoint;
import com.firebase.client.snapshot.KeyIndex;
import com.leap_app.leap.Models.User;
import com.leap_app.leap.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by aya on 4/9/16.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    Button LoginBtn;
    TextView SignUp;
    EditText LoginEmail;
    EditText LoginPassword;
    public static final String[] s1 = new String[1];




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        LoginBtn = (Button) findViewById(R.id.btn_login);
        SignUp = (TextView) findViewById(R.id.signUpHere);
        LoginEmail = (EditText) findViewById(R.id.input_email);
        LoginPassword = (EditText) findViewById(R.id.input_password);


        LoginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

//    class MyAuthResultHandler implements Firebase.AuthResultHandler{
//
//        @Override
//        public void onAuthenticated(AuthData authData){
//            switchto
//        }
//    }

    public void login() {
        Log.d(TAG, "Login");

        final String email = LoginEmail.getText().toString();
        String password = LoginPassword.getText().toString();
        final String[] s = new String[1];

        final Firebase firebase = new Firebase(com.leap_app.leap.Utility.Constants.FIREBASE_URL);
        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                final String uid = authData.getUid().toString();
                final Firebase firebase1 = new Firebase(com.leap_app.leap.Utility.Constants.FIREBASE_LEAP_USERS_URL).child(uid);
                firebase1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String key = dataSnapshot.getChildren().iterator().next().getKey();
                        final Firebase fb = new Firebase(com.leap_app.leap.Utility.Constants.FIREBASE_LEAP_USERS_URL).child(uid).child(key);
                        fb.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Log.d("User", dataSnapshot.child("name").getValue().toString());
                                s1[0] = dataSnapshot.child("name").getValue().toString();
                                MainActivity.instance.fillTextview(s1[0]);
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });



            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {

            }
        });

        if (!validate()) {
            onLoginFailed();
            return;
        }

        LoginBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        LoginBtn.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        LoginBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = LoginEmail.getText().toString();
        String password = LoginPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            LoginEmail.setError("Enter a valid email address");
            valid = false;
        } else {
            LoginEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 ) {
            LoginPassword.setError("Invalid Password");
            valid = false;
        } else {
            LoginPassword.setError(null);
        }

        return valid;
    }
}