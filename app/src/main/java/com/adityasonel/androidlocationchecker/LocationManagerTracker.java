package com.adityasonel.androidlocationchecker;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by HeisenBerg on 11/20/2017.
 */

/** Location Manager API (Framework API)
 *      -> Available from API Level 1
 *      -> Does not rely on device with Google Play Services installed
 *      -> Not recommended
 * **/

public class LocationManagerTracker extends AppCompatActivity implements LocationListener {

    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    private TextView latitude, longitude;
    private Double mLatitude, mLongitude;
    protected LocationManager manager;
    private Location location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_details);
        latitude = findViewById(R.id.tv_latitude);
        longitude = findViewById(R.id.tv_longitude);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        isGPSEnabled = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        LocationProvider gps = manager.getProvider(LocationManager.GPS_PROVIDER);
        LocationProvider network = manager.getProvider(LocationManager.NETWORK_PROVIDER);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {}
        try {
            if (!isGPSEnabled && !isNetworkEnabled) {

            } else if (isGPSEnabled) {
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 5, this);
                location = manager.getLastKnownLocation(gps.getName());
                if (location != null) {
                    mLatitude = location.getLatitude();
                    mLongitude = location.getLongitude();
                } else {
                    manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 5, this);
                    location = manager.getLastKnownLocation(network.getName());
                    if (location != null){
                        mLatitude = location.getLatitude();
                        mLongitude = location.getLongitude();
                    }
                }
            } else if (isNetworkEnabled){
                manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 3000, 5, this);
                location = manager.getLastKnownLocation(network.getName());
                if (location != null){
                    mLatitude = location.getLatitude();
                    mLongitude = location.getLongitude();
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        latitude.setText(String.valueOf(mLatitude));
        longitude.setText(String.valueOf(mLongitude));
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
        latitude.setText(String.valueOf(mLatitude));
        longitude.setText(String.valueOf(mLongitude));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (manager != null){
            manager.removeUpdates(this);
        }
    }
}
