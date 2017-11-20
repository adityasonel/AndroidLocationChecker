package com.adityasonel.androidlocationchecker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by HeisenBerg on 11/20/2017.
 */

/** Location Based Services
 *      --> Framework API(LocationManager)
 *      --> Google Play Services API
 *              -> FusedLocationProvider API(deprecated)
 *              -> FusedLocationClient API
 */

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_location_manager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LocationManagerTracker.class));
            }
        });
        findViewById(R.id.btn_fused_location_api).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FusedLocationProviderApiTracker.class));
            }
        });
        findViewById(R.id.btn_fused_location_client).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FusedLocationClientTracker.class));
            }
        });
    }
}
