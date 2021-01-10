package com.example.ezyfood_project;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    public static ArrayList<LatLng> list = new ArrayList<>();
    private final static LatLng mart_1 = new LatLng(-20.751036, -42.869928);
    public static ArrayList<Double> distances = new ArrayList<>();
    public static int index = 0;

    private final static LatLng mart_2 = new LatLng(-20.672017, -43.081624);
    private final static LatLng mart_3 = new LatLng(-20.764962, -42.868489);
    public static LatLng myLocation;
    public static String providerss;
    public boolean GPSLoaded = false;
    LocationManager locationManager;
    String latitude, longitude;
    TextView showLocation;
    private GoogleMap mMap;

    private Marker myLocationMarker;

    public LocationManager lm;
    public Criteria criteria;
    public String provider;
    public int REQUISITION_TIME_LATLONG = 5000;
    public int DISTANCE_IN_METERS = 0;
    public int ZOOM = 18;
    private static final int REQUEST_LOCATION = 1;
    private final int LOCATION_PERMISSION = 1;

    public void requestLocationPermission() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, getString(R.string.allow_gps), Toast.LENGTH_LONG).show();

                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
            }
        } else {
            updateLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    updateLocation();
                }
            }
        }
    }

    void setMyLocation(double lat, double lng) {
        myLocation = new LatLng(lat, lng);

        if (myLocationMarker != null)
            myLocationMarker.remove();

        myLocationMarker = mMap.addMarker(new MarkerOptions().position(myLocation).title("My Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        GPSLoaded = true;
    }

    public static void add_to_list(){
        list.add(mart_1);
        list.add(mart_2);
        list.add(mart_3);
    }

    public void openmainclass(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        list.add(mart_1);
        list.add(mart_2);
        list.add(mart_3);
        Button mymain_btn = (Button) findViewById(R.id.main_btn);
        mymain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmainclass();
            }
        });

        // Location Manager
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();

        // Tests if has GPS
        PackageManager packageManager = getPackageManager();
        boolean hasGPS = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS);

        // Sets criteria accuracy
        if (hasGPS) {
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            Log.i("LOCATION", "Using GPS");
        } else {
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            Log.i("LOCATION", "Using internet");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        requestLocationPermission();
    }

    @SuppressLint("MissingPermission")
    protected void updateLocation() {
        // Gets the best provider
        provider = lm.getBestProvider(criteria, true);

        if (provider == null) {
            Log.e("PROVIDER", "Provider not found!");
        } else {
            Log.i("PROVIDER", "Using: " + provider);

            // Gets location update
            lm.requestLocationUpdates(provider, REQUISITION_TIME_LATLONG, DISTANCE_IN_METERS, this);

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //updateLocation();
    }

    @Override
    protected void onDestroy() {
        // Stops location manager
        lm.removeUpdates(this);

        Log.w("PROVIDER", "Provider " + provider + " stopped!");
        super.onDestroy();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    void showDistance(){
        final Location temp = new Location(provider);
        temp.setLatitude(myLocation.latitude);
        temp.setLongitude(myLocation.longitude);

        for (int k = 0; k < list.size(); k++){
            final Location test = new Location(provider);
            test.setLatitude(list.get(k).latitude);
            test.setLongitude(list.get(k).longitude);
            double dist = temp.distanceTo(test) / 1000;
            distances.add(dist);
        }

        index = 0;
        double min = distances.get(0);
        for(int m = 1; m < distances.size(); m++){
            if(min > distances.get(m)){
                min = distances.get(m);
                index = m;
            }
        }


//        test.setLatitude(mart_2.latitude);
//        test.setLongitude(mart_2.longitude);

//        double distance = temp.distanceTo(test) / 1000;
        DecimalFormat df = new DecimalFormat("0.##");

        Toast.makeText(this, getString(R.string.distance_ap) + " from Mart "+(index+1)+ " is " + df.format(min) +" km", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        setMyLocation(lat, lng);

        Log.i("LOCATION CHANGED", "LAT=" + lat + " LONG=" + lng);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Intent it = getIntent();
        mMap = googleMap;
        it.putExtra("local","mart2");
        mMap.addMarker(new MarkerOptions().position(mart_1).title(getString(R.string.mart_1)));
        mMap.addMarker(new MarkerOptions().position(mart_2).title(getString(R.string.mart_2)));
        mMap.addMarker(new MarkerOptions().position(mart_3).title(getString(R.string.mart_3)));

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraUpdate update;
        switch (it.getStringExtra("local")) {
            case "mart1":
                update = CameraUpdateFactory.newLatLngZoom(mart_1, ZOOM);
                break;
            case "mart2":
                update = CameraUpdateFactory.newLatLngZoom(mart_2, ZOOM);
                break;
            case "mart3":
                update = CameraUpdateFactory.newLatLngZoom(mart_3, ZOOM);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + it.getStringExtra("local"));
        }
        mMap.animateCamera(update);
    }

    public void onClick_ezy_1(View v) {
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(mart_1, ZOOM);
        mMap.animateCamera(update);
    }

    public void onClick_ezy_2(View v) {
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(mart_2, ZOOM);
        mMap.animateCamera(update);
    }

    public void onClick_ezy_3(View v) {
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(mart_3, ZOOM);
        mMap.animateCamera(update);
    }

    public void onClick_myLocation(View view) {
        if(!GPSLoaded) {
            Toast.makeText(this, getString(R.string.getting_gps), Toast.LENGTH_LONG).show();
        }
        else{
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(myLocation, ZOOM);
            mMap.animateCamera(update);
            showDistance();
        }
    }

    public void onClick_changeMap(View view) {
        int type = mMap.getMapType();
        if(type >= 4)
            type = 0;

        type++;
        mMap.setMapType(type);
    }
}