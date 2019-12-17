package com.example.globalmart_teama.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globalmart_teama.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    //intialising static variables, variables for map, permissions and ImageView
    private GoogleMap mMap;
    private EditText mSearchLocationTxt;
    private static final String TAG = "MapActivity";
    private static final float DEFAULT_ZOOM = 15f;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Boolean mLocationPermissionsGranted = false;
    private ImageView continueButton;
    private static String addressLine = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //taking input for alternate location provided by user
        mSearchLocationTxt = findViewById(R.id.location_search_txt);
        //taking input for continue button to move to next page
        continueButton = findViewById(R.id.Continue_btn);

        //on continue button click listener
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuPage = new Intent(MapsActivity.this, MenuActivity.class);
                startActivity(menuPage);
            }
        });

        //initiating function to get user's permission to access the location
        getLocationPermission();
        initMap_OnSearch();
    }

    /**
     * function to initialise the map fragment
     **/
    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);
    }

    /**
     * function to initialise map based on the text input in search location bar
     **/
    private void initMap_OnSearch() {
        mSearchLocationTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER) {

                    //calling method to search new location entered by user
                    geoLocate();
                }
                return false;
            }
        });
    }

    /**
     * function to fetch the location based on the text provided by the user
     **/
    private void geoLocate() {
        String searchString = mSearchLocationTxt.getText().toString();

        Geocoder geocoder = new Geocoder(MapsActivity.this);

        //creating a list to store the address
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e) {
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }

        if (list.size() > 0) {
            Address address = list.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
            mMap.addMarker(new MarkerOptions().position(latLng).title(address.getAddressLine(0)));
        }
    }

    /**
     * function to get user permission to access the location
     **/
    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * Overriding this standard function to implement additional functionalities
     **/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        /**checking if permission granted by user**/
        if (mLocationPermissionsGranted) {

            //calling function to get device current location
            getDeviceCurrentLoc();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            initMap_OnSearch();
        }
    }

    /**
     * Based on the result of the location access request, this function is called
     **/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    //initializing the map
                    initMap();
                }
            }
        }
    }

    //function to get device current location
    private void getDeviceCurrentLoc() {

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            //checking if permission has been granted
            if (mLocationPermissionsGranted) {

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                //getting user current location and moving the camera and updating the marker with current location
                final Task currLoc = mFusedLocationProviderClient.getLastLocation();
                currLoc.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        String add= "";

                        if (task.isSuccessful()) {
                            Location currLocationIs = (Location) task.getResult();
                            LatLng latLng = new LatLng(currLocationIs.getLatitude(), currLocationIs.getLongitude());

                            Geocoder geocoder = new Geocoder(MapsActivity.this);
                            List<Address> list = new ArrayList<>();
                            try {

                                list = geocoder.getFromLocation(currLocationIs.getLatitude(), currLocationIs.getLongitude(),1);
                                Address obj = list.get(0);
                                add = obj.getAddressLine(0);
                                addressLine = add;

                                //extras.putString("address", addressLine);


                                System.out.println("Address current location : "+add);

                            } catch (IOException e) {
                                Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
                            }

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
                            mMap.addMarker(new MarkerOptions().position(latLng).title(add));
                        } else {
                            Toast.makeText(MapsActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        } catch (Exception e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }
}
