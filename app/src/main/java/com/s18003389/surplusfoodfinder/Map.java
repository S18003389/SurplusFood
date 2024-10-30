package com.s18003389.surplusfoodfinder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.s18003389.surplusfoodapp.R;

import java.io.IOException;
import java.util.List;

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    private SensorManager sm;
    private TextView tv;
    private List<Sensor> list;
    private final SensorEventListener sel = new SensorEventListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            tv.setText("x: " + values[0] + "\ny: " + values[1] + "\nz: " + values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Handle changes in sensor accuracy if needed
        }
    };

    private List<Address> listGeoCoder;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        tv = findViewById(R.id.cardView);

        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (list.size() > 0) {
            sm.registerListener(sel, list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(getBaseContext(), "No Accelerometer found!", Toast.LENGTH_SHORT).show();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Get the starting and ending points from the intent
        String startingPoint = getIntent().getStringExtra("starting_point");
        String endPoint = getIntent().getStringExtra("end_point");

        // Use the Geocoder to get coordinates for the starting and ending points
        try {
            assert startingPoint != null;
            listGeoCoder = new Geocoder(this).getFromLocationName(startingPoint, 1);
            if (listGeoCoder != null && !listGeoCoder.isEmpty()) {
                LatLng startLatLng = new LatLng(listGeoCoder.get(0).getLatitude(), listGeoCoder.get(0).getLongitude());
                Log.i("MAP_TAG", "Starting point has Longitude: " + startLatLng.longitude +
                        " Latitude: " + startLatLng.latitude);
            }

            assert endPoint != null;
            listGeoCoder = new Geocoder(this).getFromLocationName(endPoint, 1);
            if (listGeoCoder != null && !listGeoCoder.isEmpty()) {
                LatLng endLatLng = new LatLng(listGeoCoder.get(0).getLatitude(), listGeoCoder.get(0).getLongitude());
                Log.i("MAP_TAG", "End point has Longitude: " + endLatLng.longitude +
                        " Latitude: " + endLatLng.latitude);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        if (list.size() > 0) {
            sm.unregisterListener(sel);
        }
        super.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng colombo = new LatLng(6.9278012001272895, 79.85877924723391);
        googleMap.addMarker(new MarkerOptions().position(colombo).title("Marker in Colombo"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(colombo, 18.0f));
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // Check if Geocoder results are available and add a marker for the starting and ending points
        if (listGeoCoder != null && !listGeoCoder.isEmpty()) {
            LatLng geocodedLocation = new LatLng(listGeoCoder.get(0).getLatitude(), listGeoCoder.get(0).getLongitude());
            googleMap.addMarker(new MarkerOptions().position(geocodedLocation).title("Geocoded Location"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(geocodedLocation, 15.0f));
        }
    }
}
