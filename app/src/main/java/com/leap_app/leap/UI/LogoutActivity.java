package com.leap_app.leap.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.leap_app.leap.R;


public class LogoutActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Firebase firebase = new Firebase(Constants.FIREBASE_URL);
//        firebase.unauth();
//        MainActivity.instance.clearTextview();

        final ProgressDialog progressDialog = new ProgressDialog(LogoutActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Logging out...");
        progressDialog.show();


        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        progressDialog.dismiss();
                        LoginActivity.setFlag(false);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        // onLoginFailed();

                    }
                }, 3000);
    }
}
