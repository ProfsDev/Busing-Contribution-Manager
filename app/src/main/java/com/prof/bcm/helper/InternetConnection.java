package com.prof.bcm.helper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

public class InternetConnection {

    private static InternetConnection internetConnection;

    private InternetConnection() {
    }

    public static InternetConnection getInternetConnection() {
        if (internetConnection == null) {
            internetConnection = new InternetConnection();
        }
        return internetConnection;
    }

    private boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected())) {
            return true;
        } else {
            showNoInternetDialog(context);
            return false;
        }
    }

    private void showNoInternetDialog(Context context) {
        AlertDialog builder = new AlertDialog.Builder(context)
                .setMessage("You don't seem to have internet connection. Please connect to the internet to continue")
                .setCancelable(false)
                .setNegativeButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .create();
        builder.show();
    }

    public boolean isConnected(Context context) {
        return isConnectedToInternet(context);
    }
}
