package com.francis.bestroute.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.francis.bestroute.GPS.GPSTracker;
import com.francis.bestroute.R;
import com.francis.bestroute.vo.MainItemVO;
import com.google.android.gms.maps.*    ;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GPSTracker gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
         gps = new GPSTracker(this);
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

        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }
        String orders = (String) getIntent().getExtras().get("order");

        if(orders!= null && orders.equals("order")){
            List<MainItemVO> datas = (List<MainItemVO>) getIntent().getExtras().get("data");
            for (int i = 0; i < datas.size(); i++) {
                LatLng location = new LatLng(datas.get(i).getLatitude(), datas.get(i).getLongitude());
                Marker marker = mMap.addMarker(new MarkerOptions().position(location).title(""+datas.get(i).getOrder()));
                marker.showInfoWindow();
            }
            LatLng location = new LatLng(datas.get(0).getLatitude(), datas.get(0).getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f));
        }else {
            List<MainItemVO> datas = (List<MainItemVO>) getIntent().getExtras().get("data");
            MainItemVO center = (MainItemVO) getIntent().getExtras().get("center");
            // Add a marker in Sydney and move the camera
            for (int i = 0; i < datas.size(); i++) {
                LatLng location = new LatLng(datas.get(i).getLatitude(), datas.get(i).getLongitude());
                mMap.addMarker(new MarkerOptions().position(location).title(datas.get(i).getName()));
            }
            LatLng location = new LatLng(center.getLatitude(), center.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f));
        }

    }
}
