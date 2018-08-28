package com.francis.bestroute.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.francis.bestroute.GPS.GPSTracker;
import com.francis.bestroute.R;
import com.francis.bestroute.vo.MainItemVO;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends FragmentActivity implements OnMapReadyCallback {

    @Bind(R.id.confirm)
    Button confirm;
    //    @Bind(R.id.search_bar)
//    EditText searchBar;
    private GoogleMap mMap;
    private GPSTracker gps;

    private Place currentPlace;
    int PLACE_PICKER_REQUEST = 1;

    PlaceAutocompleteFragment placeAutoComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        init();
    }

    protected void init() {
        ButterKnife.bind(this);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MainItemVO vo = new MainItemVO();
//                vo.setAddress(currentPlace.getAddress().toString());
//                vo.setLatitude(currentPlace.getLatLng().latitude);
//                vo.setLongitude(currentPlace.getLatLng().longitude);
//                Intent data = new Intent();
//                data.putExtra("data",vo);
//                SearchActivity.this.setResult(1,data);
//                SearchActivity.this.finish();

                try {

                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                    startActivityForResult(builder.build(SearchActivity.this), PLACE_PICKER_REQUEST);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                mMap.clear();
                LatLng tempLocation = place.getLatLng();
                mMap.addMarker(new MarkerOptions().position(tempLocation).title(place.getAddress().toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tempLocation, 15f));
                currentPlace = place;
            }

            @Override
            public void onError(Status status) {

            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.search_map);
        mapFragment.getMapAsync(this);
        gps = new GPSTracker(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(gps.getLatitude(), gps.getLongitude());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15f));

    }




}
