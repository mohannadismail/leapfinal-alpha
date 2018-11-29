package com.leap_app.leap.ui.registeration_in_out;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.leap_app.leap.R;
import com.leap_app.leap.leap_provider.LeapDbHelper;
import com.leap_app.leap.ui.base.BaseActivity;
import com.leap_app.leap.ui.discover.MainActivity;
import com.leap_app.leap.utility.Constants;

import java.util.Objects;


public class LoginActivity extends BaseActivity {
    private static final int REQUEST_SIGNUP = 0;
    private static String s1 = "";

    public static String getId1() {
        return id1;
    }

    public static void setId1(String id1) {
        LoginActivity.id1 = id1;
    }

    private static String id1 = "";

    public static boolean isFlag() {
        return flag;
    }

    public static void setFlag(boolean flag) {
        LoginActivity.flag = flag;
    }

    private static boolean flag = false;
    private LeapDbHelper mLeapHelper;
    private Button LoginBtn;
    private TextView SignUp;
    private EditText LoginEmail;
    private EditText LoginPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initUI();

//        googlePlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Firebase ref = new Firebase(com.leap_app.leap.Utility.Constants.FIREBASE_URL);
//                ref.authWithOAuthToken(getString(R.string.google), getString(R.string.oauth_token), new Firebase.AuthResultHandler() {
//                    @Override
//                    public void onAuthenticated(AuthData authData) {
//                        try {
//                            MainActivity.class.newInstance().fillTextview(authData.getProviderData().get(getString(R.string.displayName)).toString());
//                        } catch (InstantiationException | IllegalAccessException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onAuthenticationError(com.firebase.client.FirebaseError firebaseError) {
//                        firebaseError.toException().printStackTrace();
//                    }
//
//
//                });
//            }
//        });
//
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

    private void initUI() {
        LoginBtn = findViewById(R.id.btn_login);
        SignUp = findViewById(R.id.signUpHere);
        LoginEmail = findViewById(R.id.input_email);
        LoginPassword = findViewById(R.id.input_password);
        mLeapHelper = new LeapDbHelper(getApplicationContext());
    }

    public void login() {
        final String email = LoginEmail.getText().toString();
        String password = LoginPassword.getText().toString();

        SQLiteDatabase db = mLeapHelper.getReadableDatabase();
        final Cursor c = db.rawQuery("SELECT id FROM User WHERE email =? AND password =? ", new String[]{email, password});


        final String[] s = new String[1];

        final Firebase firebase = new Firebase(com.leap_app.leap.utility.Constants.FIREBASE_URL);
        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        final String uid = authData.getUid();
                        final Firebase firebase1 = new Firebase(Constants.FIREBASE_LEAP_USERS_URL).child(uid);
                        firebase1.addValueEventListener(new com.firebase.client.ValueEventListener() {
                            @Override
                            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                String key = null;
                                if (dataSnapshot.getChildren().iterator().hasNext()) {
                                    key = dataSnapshot.getChildren().iterator().next().getKey();
                                    assert key != null;
                                    final Firebase fb = new Firebase(com.leap_app.leap.utility.Constants.FIREBASE_LEAP_USERS_URL).child(uid).child(key);
                                    fb.addValueEventListener(new com.firebase.client.ValueEventListener() {
                                        @Override
                                        public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                                            if (null != dataSnapshot)
                                                s1 = dataSnapshot
                                                        .getChildren()
                                                        .iterator()
                                                        .next()
                                                        .child(getString(R.string.name))
                                                        .getValue() + "";
//                                            MainActivity.getInstance().fillTextview(s1);
                                        }

                                        @Override
                                        public void onCancelled(com.firebase.client.FirebaseError firebaseError) {
                                            firebaseError.toException().printStackTrace();

                                        }

                                    });
                                }
                            }

                            @Override
                            public void onCancelled(com.firebase.client.FirebaseError firebaseError) {
                                firebaseError.toException().printStackTrace();
                            }

                        });
                    }


                    @Override
                    public void onAuthenticationError(com.firebase.client.FirebaseError firebaseError) {
                        firebaseError.toException().printStackTrace();
                    }
                }
        );

        if (!validate()) {
            onLoginFailed();
            return;
        }

        LoginBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getString(R.string.authenticating));
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if (c != null) {
                            c.moveToFirst();
                            if (c.getCount() > 0) {
                                id1 = c.getString(0);
                                c.close();
                                onLoginSuccess();
                            } else {
                                c.close();
                                onLoginFailed();
                            }
                        }
                        progressDialog.dismiss();

                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }

//        /* Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...); */
//        if (requestCode == RC_GOOGLE_LOGIN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResult(result);
//        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // disable going back to the MainActivity
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    public void onLoginSuccess() {
        LoginBtn.setEnabled(true);
        SQLiteDatabase db = mLeapHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM User WHERE id =?", new String[]{id1});
        if (c != null) {
            c.moveToFirst();
        }
        s1 = Objects.requireNonNull(c).getString(0);

        //TODO:: fix this shit
        flag = true;
        c.close();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(Constants.Name1, s1);
        startActivity(intent);
        finish();
    }


    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), R.string.login_failed, Toast.LENGTH_LONG).show();
        flag = false;
        LoginBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = LoginEmail.getText().toString();
        String password = LoginPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            LoginEmail.setError(getString(R.string.enter_valid_mail));
            valid = false;
        } else {
            LoginEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            LoginPassword.setError(getString(R.string.invalid_password));
            valid = false;
        } else {
            LoginPassword.setError(null);
        }

        return valid;
    }

    /**
     * Signs you into ShoppingList++ using the Google Login Provider
     *
     * @param token A Google OAuth access token returned from Google
     */
//    private void loginWithGoogle(String token) {
//        Firebase mFirebaseRef = new Firebase(com.leap_app.leap.Utility.Constants.FIREBASE_URL);
//        mFirebaseRef.authWithOAuthToken("google", token, new MyAuthResultHandler("google"));
//    }

    /**
     * GOOGLE SIGN IN CODE
     *
     * This code is mostly boiler plate from
     * https://developers.google.com/identity/sign-in/android/start-integrating
     * and
     * https://github.com/googlesamples/google-services/blob/master/android/signin/app/src/main/java/com/google/samples/quickstart/signin/SignInActivity.java
     *
     * The big picture steps are:
     * 1. User clicks the sign in with Google button
     * 2. An intent is started for sign in.
     *      - If the connection fails it is caught in the onConnectionFailed callback
     *      - If it finishes, onActivityResult is called with the correct request code.
     * 3. If the sign in was successful, set the mGoogleAccount to the current account and
     * then call get GoogleOAuthTokenAndLogin
     * 4. getGoogleOAuthTokenAndLogin launches an AsyncTask to get an OAuth2 token from Google.
     * 5. Once this token is retrieved it is available to you in the onPostExecute method of
     * the AsyncTask. **This is the token required by Firebase**
     */


    /* Sets up the Google Sign In Button : https://developers.google.com/android/reference/com/google/android/gms/common/SignInButton */
//    private void setupGoogleSignIn() {
//        SignInButton signInButton = (SignInButton)findViewById(R.id.login_with_google);
//        signInButton.setSize(SignInButton.SIZE_WIDE);
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onSignInGooglePressed(v);
//            }
//        });
}

/**
 * Sign in with Google plus when user clicks "Sign in with Google" textView (button)
 */
//    public void onSignInGooglePressed(View view) {
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_GOOGLE_LOGIN);
//        mAuthProgressDialog.show();
//
//    }
//
//    private void showErrorToast(String message) {
//        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
//    }

//    @Override
//    public void onConnectionFailed(ConnectionResult result) {
//        /**
//         * An unresolvable error has occurred and Google APIs (including Sign-In) will not
//         * be available.
//         */
//        mAuthProgressDialog.dismiss();
//        showErrorToast(result.toString());
//    }


//    private void handleSignInResult(GoogleSignInResult result) {
//        Log.d("LOG", "handleSignInResult:" + result.isSuccess());
//        if (result.isSuccess()) {
//            /* Signed in successfully, get the OAuth token */
//            mGoogleAccount = result.getSignInAccount();
//            getGoogleOAuthTokenAndLogin();
//
//
//        } else {
//            if (result.getStatus().getStatusCode() == GoogleSignInStatusCodes.SIGN_IN_CANCELLED) {
//                showErrorToast("The sign in was cancelled. Make sure you're connected to the internet and try again.");
//            } else {
//                showErrorToast("Error handling the sign in: " + result.getStatus().getStatusMessage());
//            }
//            mAuthProgressDialog.dismiss();
//        }
//    }

//    /**
//     * Gets the GoogleAuthToken and logs in.
//     */
//    private void getGoogleOAuthTokenAndLogin() {
//        /* Get OAuth token in Background */
//        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
//            String mErrorMessage = null;
//
//            @Override
//            protected String doInBackground(Void... params) {
//                String token = null;
//
//                try {
//                    String scope = String.format(getString(R.string.oauth2_format), new Scope(Scopes.PROFILE)) + " email";
//
//                    token = GoogleAuthUtil.getToken(LoginActivity.this, mGoogleAccount.getEmail(), scope);
//                } catch (IOException transientEx) {
//                    /* Network or server error */
//                    Log.e("LOG", getString(R.string.google_error_auth_with_google) + transientEx);
//                    mErrorMessage = getString(R.string.google_error_network_error) + transientEx.getMessage();
//                } catch (UserRecoverableAuthException e) {
//                    Log.w("LOG", getString(R.string.google_error_recoverable_oauth_error) + e.toString());
//
//                    /* We probably need to ask for permissions, so start the intent if there is none pending */
//                    if (!mGoogleIntentInProgress) {
//                        mGoogleIntentInProgress = true;
//                        Intent recover = e.getIntent();
//                        startActivityForResult(recover, RC_GOOGLE_LOGIN);
//                    }
//                } catch (GoogleAuthException authEx) {
//                    /* The call is not ever expected to succeed assuming you have already verified that
//                     * Google Play services is installed. */
//                    Log.e("LOG", " " + authEx.getMessage(), authEx);
//                    mErrorMessage = getString(R.string.google_error_auth_with_google) + authEx.getMessage();
//                }
//                return token;
//            }
//
//            @Override
//            protected void onPostExecute(String token) {
//                mAuthProgressDialog.dismiss();
//                if (token != null) {
//                    /* Successfully got OAuth token, now login with Google */
//                    loginWithGoogle(token);
//                } else if (mErrorMessage != null) {
//                    showErrorToast(mErrorMessage);
//                }
//            }
//        };
//
//        task.execute();
//    }
//
//class MyAuthResultHandler implements Firebase.AuthResultHandler {
//
//    private final String provider;
//    /**
//     * Variables related to Google Login
//     */
//    /* A flag indicating that a PendingIntent is in progress and prevents us from starting further intents. */
//    private boolean mGoogleIntentInProgress;
//    /* Request code used to invoke sign in user interactions for Google+ */
//    private static final int RC_GOOGLE_LOGIN = 1;
//    /* A Google account object that is populated if the user signs in with Google */
//    private GoogleSignInAccount mGoogleAccount;
//    private ProgressDialog mAuthProgressDialog;
//
//
//
//    public MyAuthResultHandler(String provider) {
//        this.provider = provider;
//    }
//
//    /**
//     * On successful authentication call setAuthenticatedUser if it was not already
//     * called in
//     */
//    @Override
//    public void onAuthenticated(AuthData authData) {
//        mAuthProgressDialog.dismiss();
//        Log.i("LOG", provider + " " + getString(R.string.log_message_auth_successful));
//        if (authData != null) {
//            /* Go to main activity */
//        }
//    }
//
//    @Override
//    public void onAuthenticationError(FirebaseError firebaseError) {
//        mAuthProgressDialog.dismiss();
//        showErrorToast(firebaseError.toString());
//    }
//}

