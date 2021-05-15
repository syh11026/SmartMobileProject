package com.smartmobileproject.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.smartmobileproject.function.KaKaoMap_funtion;
import com.smartmobileproject.activity.R;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MapActivity extends AppCompatActivity {
    double longtitude;
    double latitude;
    LocationManager locationManager;
    LocationListener locationListener;
    Location location;
    MapPoint mapPoint ;
    KaKaoMap_funtion kaKaoMap_funtion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        kaKaoMap_funtion = new KaKaoMap_funtion();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.d("툴바", String.valueOf(toolbar));

        MapView mapView = new MapView(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                longtitude = location.getLongitude();
                latitude = location.getLatitude();
                mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longtitude), true);

            }
            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("권한설정", "권한설정을 해주세요.");
        }
        if (LocationManager.GPS_PROVIDER != null) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1000, locationListener);
        } else if (LocationManager.NETWORK_PROVIDER != null) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1000, locationListener);
        } else {
            Log.d("위치정보", "위치정보를 불러올수가 없습니다......ㅠㅠㅠ");
        }

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapPoint = MapPoint.mapPointWithGeoCoord(37.55,127);
        Log.d("map", String.valueOf(mapPoint));
        kaKaoMap_funtion.addCustomMarker(mapView,mapPoint);
        mapViewContainer.addView(mapView);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bar,menu);
        Log.d("툴바","1");
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menubutton:
                Intent NewActivity = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(NewActivity);
                break;
        }
        Log.d("툴바","2");

        return true;
    }
}