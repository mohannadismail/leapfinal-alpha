package com.leap_app.leap.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leap_app.leap.R;

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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        SignInLink = (TextView) findViewById(R.id.LoginHere);
        SignUpBtn = (Button) findViewById(R.id.signUpBtn);
        SignUpEmail = (EditText) findViewById(R.id.up_input_email);
        SignUpName = (EditText) findViewById(R.id.up_input_name);
        SignUpPassword = (EditText) findViewById(R.id.up_input_password);

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

        String name = SignUpName.getText().toString();
        String email = SignUpEmail.getText().toString();
        String password = SignUpPassword.getText().toString();

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
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

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
