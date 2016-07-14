package com.leap_app.leap.UI;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.leap_app.leap.LeapProvider.LeapContract;
import com.leap_app.leap.LeapProvider.LeapDbHelper;
import com.leap_app.leap.R;
import com.leap_app.leap.Utility.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aya on 4/9/16.
 */

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    EditText SignUpName;
    EditText SignUpEmail;
    EditText SignUpPassword;
    Button SignUpBtn;
    TextView SignInLink;
    public static LeapDbHelper mLeapHelper;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        SignInLink = (TextView) findViewById(R.id.LoginHere);
        SignUpBtn = (Button) findViewById(R.id.signUpBtn);
        SignUpEmail = (EditText) findViewById(R.id.up_input_email);
        SignUpName = (EditText) findViewById(R.id.up_input_name);
        SignUpPassword = (EditText) findViewById(R.id.up_input_password);
        mLeapHelper = new LeapDbHelper(getApplicationContext());
        SignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        SignInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);

            }
        });
    }

    public void signup() {

        final String name = SignUpName.getText().toString();
        final String email = SignUpEmail.getText().toString();
        String password = SignUpPassword.getText().toString();

        SQLiteDatabase db = mLeapHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(LeapContract.UserEntry.COLUMN_Name,name);
        values.put(LeapContract.UserEntry.COLUMN_Email,email);
        values.put(LeapContract.UserEntry.COLUMN_password,password);

        db.insert(LeapContract.UserEntry.Table_Name, null, values);


//        final Firebase firebase = new Firebase(Constants.FIREBASE_URL).child("users");
//        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
//            @Override
//            public void onSuccess(Map<String, Object> stringObjectMap) {
//
//                Toast.makeText(getBaseContext(),"Successfully created user account",Toast.LENGTH_LONG).show();
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("email", email);
//                map.put("name", name);
//                Firebase fb = firebase.child(stringObjectMap.get("uid").toString()).push();
//                fb.setValue(map);
//            }
//
//            @Override
//            public void onError(FirebaseError firebaseError) {
//                Toast.makeText(getBaseContext(),"Account was not created. Please try again",Toast.LENGTH_LONG).show();
//            }
//        });
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        SignUpBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        SignUpBtn.setEnabled(true);
        setResult(RESULT_OK, null);
        Toast.makeText(getBaseContext(),"Successfully created user account",Toast.LENGTH_LONG).show();
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        SignUpBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = SignUpName.getText().toString();
        String email = SignUpEmail.getText().toString();
        String password = SignUpPassword.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            SignUpName.setError("at least 3 characters");
            valid = false;
        } else {
            SignUpName.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            SignUpEmail.setError("enter a valid email address");
            valid = false;
        } else {
            SignUpEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 ) {
            SignUpPassword.setError("Invalid Password");
            valid = false;
        } else {
            SignUpPassword.setError(null);
        }

        return valid;
    }

}
