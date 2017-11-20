package com.adityasonel.androidlocationchecker;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by HeisenBerg on 11/20/2017.
 */

/** FusedLocationClient API (Google PlayServices API)
 *      -> Updated version of FusedLocationProvider API
 *      -> Better power and provider management
 */

public class FusedLocationClientTracker extends AppCompatActivity {

    private TextView latitude, longitude;
    private Double mLatitude, mLongitude;
    private FusedLocationProviderClient providerClient;
    protected Location location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_details);
        latitude = findViewById(R.id.tv_latitude);
        longitude = findViewById(R.id.tv_longitude);

        providerClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getLocation();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        providerClient.getLastLocation().addOnCompleteListener(this, new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful() && task.getResult() != null){
                    location = task.getResult();
                    mLatitude = location.getLatitude();
                    mLongitude = location.getLongitude();
                    latitude.setText(String.valueOf(mLatitude));
                    longitude.setText(String.valueOf(mLongitude));
                }
            }
        });
    }
}
