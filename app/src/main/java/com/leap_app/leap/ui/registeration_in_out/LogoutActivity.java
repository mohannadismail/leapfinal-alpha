package com.leap_app.leap.ui.registeration_in_out;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.leap_app.leap.R;
import com.leap_app.leap.ui.base.BaseActivity;
import com.leap_app.leap.ui.discover.MainActivity;
import com.leap_app.leap.utility.Constants;


public class LogoutActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase firebase = new Firebase(Constants.FIREBASE_URL);
        firebase.unauth();
        try {
            MainActivity.class.newInstance().clearTextview();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        final ProgressDialog progressDialog = new ProgressDialog(LogoutActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.logging_out_msg));
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
//                         onLoginFailed();

                    }
                }, 3000);
    }
}
