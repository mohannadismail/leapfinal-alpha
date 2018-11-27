package com.leap_app.leap.UI;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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


public class SignupActivity extends BaseActivity {
    private static LeapDbHelper mLeapHelper;
    private EditText SignUpName;
    private EditText SignUpEmail;
    private EditText SignUpPassword;
    private Button SignUpBtn;
    private TextView SignInLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        initUI();
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

    private void initUI() {
        SignInLink = findViewById(R.id.LoginHere);
        SignUpBtn = findViewById(R.id.signUpBtn);
        SignUpEmail = findViewById(R.id.up_input_email);
        SignUpName = findViewById(R.id.up_input_name);
        SignUpPassword = findViewById(R.id.up_input_password);
        mLeapHelper = new LeapDbHelper(getApplicationContext());
    }

    public void signup() {

        final String name = SignUpName.getText().toString();
        final String email = SignUpEmail.getText().toString();
        String password = SignUpPassword.getText().toString();

        SQLiteDatabase db = mLeapHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(LeapContract.UserEntry.COLUMN_Name, name);
        values.put(LeapContract.UserEntry.COLUMN_Email, email);
        values.put(LeapContract.UserEntry.COLUMN_password, password);

        db.insert(LeapContract.UserEntry.Table_Name, null, values);


        final Firebase firebase = new Firebase(Constants.FIREBASE_URL).child(getString(R.string.users));
        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {

                Toast.makeText(getBaseContext(), R.string.successfully_created_user, Toast.LENGTH_LONG).show();
                Map<String, String> map = new HashMap<String, String>();
                map.put(getString(R.string.emailll), email);
                map.put(getString(R.string.nname), name);
                Firebase fb = firebase.child(stringObjectMap.get(getString(R.string.uid)).toString()).push();
                fb.setValue(map);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(getBaseContext(), R.string.account_not_created, Toast.LENGTH_LONG).show();
            }
        });
        Log.d(SignupActivity.class.getName(), getString(R.string.signup));

        if (!validate()) {
            onSignupFailed();
            return;
        }

        SignUpBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.creating_account));
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
        Toast.makeText(getBaseContext(), R.string.account_created_successfully, Toast.LENGTH_LONG).show();
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), R.string.signup_failed, Toast.LENGTH_LONG).show();

        SignUpBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = SignUpName.getText().toString();
        String email = SignUpEmail.getText().toString();
        String password = SignUpPassword.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            SignUpName.setError(getString(R.string.atleast_3_chars));
            valid = false;
        } else {
            SignUpName.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            SignUpEmail.setError(getString(R.string.enter_valid_mail));
            valid = false;
        } else {
            SignUpEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            SignUpPassword.setError(getString(R.string.invalid_password));
            valid = false;
        } else {
            SignUpPassword.setError(null);
        }

        return valid;
    }

}
