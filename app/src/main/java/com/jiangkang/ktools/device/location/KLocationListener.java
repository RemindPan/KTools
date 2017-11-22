package com.jiangkang.ktools.device.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/**
 * Created by jiangkang on 2017/10/27.
 * description：
 */

public class KLocationListener implements LocationListener{


    private static final String TAG = "location";
    private Context mContext;

    public KLocationListener(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG,location.toString());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, "onStatusChanged: " + provider + "status:" + status);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "onProviderEnabled: " + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "onProviderDisabled: " + provider);
    }
}
