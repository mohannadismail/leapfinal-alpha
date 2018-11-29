package com.leap_app.leap.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.leap_app.leap.R;

import java.util.ArrayList;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class PermissionHandler {

    private Context context;
    private SharedPreferences sharedPreferences;
    private String[] perms = {
            context.getString(R.string.storage_perm),
            context.getString(R.string.location_perm),
            context.getString(R.string.fineLocation_perm),
            context.getString(R.string.getAccounts_perm),
            context.getString(R.string.useCredintials_perm)};
    private int permsRequestCode = 200;

    public PermissionHandler(Context context) {
        this.context = context;
    }

    private void requestPermissionForLeap(ActivityCompat context, Activity activity) {
        ActivityCompat.requestPermissions(activity, perms, permsRequestCode);

    }

    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {

        switch (permsRequestCode) {

            case 200:

                boolean storageAccepted = grantResults[0] == PERMISSION_GRANTED;

                boolean coarseLocationAccepted = grantResults[1] == PERMISSION_GRANTED;

                boolean fineLocationAccepted = grantResults[2] == PERMISSION_GRANTED;

                boolean getAccountsAccepted = grantResults[3] == PERMISSION_GRANTED;

                boolean useCredintialsAccepted = grantResults[4] == PERMISSION_GRANTED;

                break;

        }

    }

    private boolean canMakeSmores() {

        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);

    }


    private boolean hasPermission(String permission) {

        if (canMakeSmores()) {

            return (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED);
        }

        return true;

    }

    private boolean shouldWeAsk(String permission) {

        return (sharedPreferences.getBoolean(permission, true));

    }


    private void markAsAsked(String permission) {
        sharedPreferences = context.getSharedPreferences("", 0);
        sharedPreferences.edit().putBoolean(permission, false).apply();

    }

    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {

        ArrayList<String> result = new ArrayList<>();

        for (String perm : wanted) {

            if (!hasPermission(perm) && shouldWeAsk(perm)) {

                result.add(perm);

            }

        }

        return result;

    }
}